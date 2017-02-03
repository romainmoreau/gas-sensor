package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractGasSensorClient<E extends GasSensorEvent> implements GasSensorClient<E> {
	private final String sensorName;

	private final int length;

	private final int checksumLength;

	private final byte[] header;

	private final List<Byte> buffer;

	private final GasSensorEventListener<E> gasSensorEventListener;

	private final GasSensorExceptionHandler gasSensorExceptionHandler;

	private final GasSensorReader gasSensorReader;

	public AbstractGasSensorClient(String sensorName, GasSensorReaderFactory<E> gasSensorReaderFactory,
			GasSensorEventListener<E> gasSensorEventListener, GasSensorExceptionHandler gasSensorExceptionHandler,
			int length, int checksumLength, byte... header) throws IOException {
		if (header.length > length) {
			throw new IllegalArgumentException("Header too long");
		}
		if (gasSensorEventListener == null) {
			throw new IllegalArgumentException("Empty listener");
		}
		if (gasSensorExceptionHandler == null) {
			throw new IllegalArgumentException("Empty exception handler");
		}
		this.sensorName = sensorName;
		this.length = length;
		this.checksumLength = checksumLength;
		this.header = header;
		this.buffer = new ArrayList<>();
		this.gasSensorEventListener = gasSensorEventListener;
		this.gasSensorExceptionHandler = gasSensorExceptionHandler;
		this.gasSensorReader = gasSensorReaderFactory.construct(this);
	}

	@Override
	public GasSensorEventListener<E> getGasSensorEventListener() {
		return gasSensorEventListener;
	}

	@Override
	public GasSensorExceptionHandler getGasSensorExceptionHandler() {
		return gasSensorExceptionHandler;
	}

	@Override
	public String getSensorName() {
		return sensorName;
	}

	@Override
	public void close() throws IOException {
		gasSensorReader.close();
	}

	@Override
	public void onReadBytes(byte[] readBytes) {
		for (byte readByte : readBytes) {
			buffer.add(readByte);
		}
		checkForEvent();
	}

	private void checkForEvent() {
		while (buffer.size() >= length) {
			byte[] eventHeader = getHeader();
			if (!Arrays.equals(header, eventHeader)) {
				gasSensorExceptionHandler.onIgnoredByte(buffer.remove(0),
						"received header " + Arrays.toString(eventHeader) + " is different from expected header "
								+ Arrays.toString(header));
			} else {
				break;
			}
		}
		if (buffer.size() < length) {
			return;
		}
		byte[] event = getEvent();
		byte[] checksum = getChecksum(event);
		byte[] expectedChecksum = calculateChecksum(event);
		if (!Arrays.equals(expectedChecksum, checksum)) {
			Iterator<Byte> invalidHeaderByteIterator = buffer.subList(0, header.length).iterator();
			while (invalidHeaderByteIterator.hasNext()) {
				byte invalidHeaderByte = invalidHeaderByteIterator.next();
				invalidHeaderByteIterator.remove();
				gasSensorExceptionHandler.onIgnoredByte(invalidHeaderByte,
						"received checksum " + Arrays.toString(checksum) + " is different from expected checksum "
								+ Arrays.toString(expectedChecksum));
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

	private byte[] getChecksum(byte[] event) {
		return Arrays.copyOfRange(event, event.length - checksumLength, event.length);
	}

	private void onEvent(byte[] event) {
		gasSensorEventListener.onGasSensorEvent(getSensorName(), eventToGasSensorEvent(event));
	}

	protected abstract E eventToGasSensorEvent(byte[] event);

	protected abstract byte[] calculateChecksum(byte[] event);
}
