package fr.romainmoreau.gassensor.client.common;

public interface GasSensorExceptionHandler {
	void onIgnoredByte(byte ignoredByte, String cause);

	void onReadBytesError(Exception exception);
}
