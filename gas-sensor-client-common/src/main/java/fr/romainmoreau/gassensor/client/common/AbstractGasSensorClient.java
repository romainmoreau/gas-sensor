package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractGasSensorClient<E extends GasSensorEvent> implements GasSensorClient<E> {
	private final String sensorName;

	private final int checksumLength;

	private final byte[] header;

	private final List<Byte> buffer;

	private final GasSensorEventListener<E> gasSensorEventListener;

	private final GasSensorExceptionHandler gasSensorExceptionHandler;

	private final GasSensorEventAnalyser gasSensorEventAnalyser;

	private final GasSensorReader gasSensorReader;

	public AbstractGasSensorClient(String sensorName, GasSensorReaderFactory<E> gasSensorReaderFactory,
			GasSensorEventListener<E> gasSensorEventListener, GasSensorExceptionHandler gasSensorExceptionHandler,
			GasSensorEventAnalyser gasSensorEventAnalyser, int checksumLength, byte... header) throws IOException {
		if (gasSensorEventListener == null) {
			throw new IllegalArgumentException("Empty listener");
		}
		if (gasSensorExceptionHandler == null) {
			throw new IllegalArgumentException("Empty exception handler");
		}
		this.sensorName = sensorName;
		this.gasSensorEventAnalyser = gasSensorEventAnalyser;
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
		while (buffer.size() >= header.length) {
			byte[] eventHeader = getHeader();
			if (!Arrays.equals(header, eventHeader)) {
				gasSensorExceptionHandler.onIgnoredByte(buffer.remove(0),
						"received header " + Arrays.toString(eventHeader) + " is different from expected header "
								+ Arrays.toString(header));
			} else {
				break;
			}
		}
		if (!gasSensorEventAnalyser.isContainingAtLeastOneEvent(buffer)) {
			return;
		}
		byte[] event = gasSensorEventAnalyser.getFirstEvent(buffer);
		if (checksumLength > 0) {
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
				onEvent(event);
			}
		} else {
			onEvent(event);
		}
		checkForEvent();
	}

	private byte[] getHeader() {
		return ByteUtils.getNFirstBytes(header.length, buffer);
	}

	private byte[] getChecksum(byte[] event) {
		return Arrays.copyOfRange(event, event.length - checksumLength, event.length);
	}

	private void onEvent(byte[] event) {
		buffer.subList(0, event.length).clear();
		gasSensorEventListener.onGasSensorEvent(getSensorName(), eventToGasSensorEvent(event));
	}

	protected abstract E eventToGasSensorEvent(byte[] event);

	protected abstract byte[] calculateChecksum(byte[] event);
}
