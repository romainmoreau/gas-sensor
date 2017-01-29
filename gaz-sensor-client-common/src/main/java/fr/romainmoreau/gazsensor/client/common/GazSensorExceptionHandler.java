package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;

public interface GazSensorExceptionHandler {
	void onIgnoredByte(byte ignoredByte);

	void onReadBytesError(IOException ioException);
}
