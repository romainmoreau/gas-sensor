package fr.romainmoreau.gazsensor.client.common;

import java.io.Closeable;
import java.io.IOException;

public interface GazSensorClient<E extends GazSensorEvent> extends Closeable {
	String getSensorName();

	GazSensorEventListener<E> getGazSensorEventListener();

	void onIgnoredByte(byte ignoredByte);

	void onReadBytes(byte[] readBytes);

	void onReadBytesError(IOException ioException);
}
