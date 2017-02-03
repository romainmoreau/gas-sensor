package fr.romainmoreau.gassensor.client.common;

import java.io.Closeable;

public interface GasSensorClient<E extends GasSensorEvent> extends Closeable {
	String getSensorName();

	GasSensorEventListener<E> getGasSensorEventListener();

	GasSensorExceptionHandler getGasSensorExceptionHandler();

	void onReadBytes(byte[] readBytes);
}
