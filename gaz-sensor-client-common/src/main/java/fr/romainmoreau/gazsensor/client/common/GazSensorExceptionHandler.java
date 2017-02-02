package fr.romainmoreau.gazsensor.client.common;

public interface GazSensorExceptionHandler {
	void onIgnoredByte(byte ignoredByte, String cause);

	void onReadBytesError(Exception exception);
}
