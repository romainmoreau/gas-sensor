package fr.romainmoreau.gassensor.client.common;

public interface GasSensorExceptionHandler {
	void onIgnoredByte(String sensorName, byte ignoredByte, String cause);

	void onReadBytesError(String sensorName, Exception exception);
}
