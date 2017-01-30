package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractGazSensorClient<E extends GazSensorEvent> implements GazSensorClient<E> {
	private final String sensorName;

	private final int length;

	private final int checksumLength;

	private final byte[] header;

	private final List<Byte> buffer;

	private final GazSensorEventListener<E> gazSensorEventListener;

	private final GazSensorExceptionHandler gazSensorExceptionHandler;

	private final GazSensorReader gazSensorReader;

	public AbstractGazSensorClient(String sensorName, GazSensorReaderFactory<E> gazSensorReaderFactory,
			GazSensorEventListener<E> gazSensorEventListener, GazSensorExceptionHandler gazSensorExceptionHandler,
			int length, int checksumLength, byte... header) throws IOException {
		if (header.length > length) {
			throw new IllegalArgumentException("Header too long");
		}
		if (gazSensorEventListener == null) {
			throw new IllegalArgumentException("Empty listener");
		}
		if (gazSensorExceptionHandler == null) {
			throw new IllegalArgumentException("Empty exception handler");
		}
		this.sensorName = sensorName;
		this.length = length;
		this.checksumLength = checksumLength;
		this.header = header;
		this.buffer = new ArrayList<>();
		this.gazSensorEventListener = gazSensorEventListener;
		this.gazSensorExceptionHandler = gazSensorExceptionHandler;
		this.gazSensorReader = gazSensorReaderFactory.construct(this);
	}

	@Override
	public GazSensorEventListener<E> getGazSensorEventListener() {
		return gazSensorEventListener;
	}

	@Override
	public GazSensorExceptionHandler getGazSensorExceptionHandler() {
		return gazSensorExceptionHandler;
	}

	@Override
	public String getSensorName() {
		return sensorName;
	}

	@Override
	public void close() throws IOException {
		gazSensorReader.close();
	}

	@Override
	public void onReadBytes(byte[] readBytes) {
		for (byte readByte : readBytes) {
			buffer.add(readByte);
		}
		checkForEvent();
	}

	private void checkForEvent() {
		while (buffer.size() >= length && !Arrays.equals(header, getHeader())) {
			gazSensorExceptionHandler.onIgnoredByte(buffer.remove(0));
		}
		if (buffer.size() < length) {
			return;
		}
		byte[] event = getEvent();
		if (!isChecksumValid(event)) {
			Iterator<Byte> invalidHeaderByteIterator = buffer.subList(0, header.length).iterator();
			while (invalidHeaderByteIterator.hasNext()) {
				byte invalidHeaderByte = invalidHeaderByteIterator.next();
				invalidHeaderByteIterator.remove();
				gazSensorExceptionHandler.onIgnoredByte(invalidHeaderByte);
			}
		} else {
			buffer.subList(0, length).clear();
			onEvent(event);
		}
		checkForEvent();
	}

	private byte[] getNFirstBytes(int n) {
		List<Byte> nFirstByteList = buffer.subList(0, n);
		byte[] nFirstBytes = new byte[n];
		for (int i = 0; i < nFirstByteList.size(); i++) {
			nFirstBytes[i] = nFirstByteList.get(i);
		}
		return nFirstBytes;
	}

	private byte[] getHeader() {
		return getNFirstBytes(header.length);
	}

	private byte[] getEvent() {
		return getNFirstBytes(length);
	}

	private boolean isChecksumValid(byte[] event) {
		byte[] checksum = getChecksum(event);
		byte[] expectedChecksum = calculateChecksum(event);
		return Arrays.equals(expectedChecksum, checksum);
	}

	private byte[] getChecksum(byte[] event) {
		return Arrays.copyOfRange(event, event.length - checksumLength, event.length);
	}

	private void onEvent(byte[] event) {
		gazSensorEventListener.onGazSensorEvent(getSensorName(), eventToGazSensorEvent(event));
	}

	protected abstract E eventToGazSensorEvent(byte[] event);

	protected abstract byte[] calculateChecksum(byte[] event);
}
