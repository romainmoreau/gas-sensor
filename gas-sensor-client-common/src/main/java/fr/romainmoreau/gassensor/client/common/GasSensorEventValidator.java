package fr.romainmoreau.gassensor.client.common;

public interface GasSensorEventValidator {
	boolean isValid(byte[] event);
}
