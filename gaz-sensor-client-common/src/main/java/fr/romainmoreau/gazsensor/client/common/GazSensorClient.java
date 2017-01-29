package fr.romainmoreau.gazsensor.client.common;

import java.io.Closeable;

public interface GazSensorClient<E extends GazSensorEvent> extends Closeable {
	String getSensorName();

	GazSensorEventListener<E> getGazSensorEventListener();

	GazSensorExceptionHandler getGazSensorExceptionHandler();

	void onReadBytes(byte[] readBytes);
}
