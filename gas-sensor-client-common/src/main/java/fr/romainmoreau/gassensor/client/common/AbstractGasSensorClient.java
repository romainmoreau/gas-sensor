package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractGasSensorClient<E extends GasSensorEvent> implements GasSensorClient<E> {
	private final String sensorName;

	private final byte[] header;

	private final List<Byte> buffer;

	private final GasSensorEventListener<E> gasSensorEventListener;

	private final GasSensorExceptionHandler gasSensorExceptionHandler;

	private final GasSensorEventAnalyser gasSensorEventAnalyser;

	private final GasSensorEventValidator gasSensorEventValidator;

	private final GasSensorReader gasSensorReader;

	public AbstractGasSensorClient(String sensorName, GasSensorReaderFactory<E> gasSensorReaderFactory,
			GasSensorEventListener<E> gasSensorEventListener, GasSensorExceptionHandler gasSensorExceptionHandler,
			GasSensorEventAnalyser gasSensorEventAnalyser, GasSensorEventValidator gasSensorEventValidator,
			byte... header) throws IOException {
		if (gasSensorEventListener == null) {
			throw new IllegalArgumentException("Empty listener");
		}
		if (gasSensorExceptionHandler == null) {
			throw new IllegalArgumentException("Empty exception handler");
		}
		if (gasSensorEventAnalyser == null) {
			throw new IllegalArgumentException("Empty analyser");
		}
		if (gasSensorEventValidator == null) {
			throw new IllegalArgumentException("Empty validator");
		}
		this.sensorName = sensorName;
		this.gasSensorEventAnalyser = gasSensorEventAnalyser;
		this.gasSensorEventValidator = gasSensorEventValidator;
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
		if (gasSensorEventValidator.isValid(event)) {
			onEvent(event);
		} else {
			gasSensorExceptionHandler.onIgnoredByte(buffer.remove(0),
					"event " + Arrays.toString(event) + " is not valid");
		}
		checkForEvent();
	}

	private byte[] getHeader() {
		return ByteUtils.getNFirstBytes(header.length, buffer);
	}

	private void onEvent(byte[] event) {
		buffer.subList(0, event.length).clear();
		gasSensorEventListener.onGasSensorEvent(getSensorName(), eventToGasSensorEvent(event));
	}

	protected abstract E eventToGasSensorEvent(byte[] event);
}
