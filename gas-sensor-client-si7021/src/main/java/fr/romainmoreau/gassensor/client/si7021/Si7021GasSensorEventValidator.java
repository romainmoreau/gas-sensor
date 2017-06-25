package fr.romainmoreau.gassensor.client.si7021;

import fr.romainmoreau.gassensor.client.common.GasSensorEventValidator;

public class Si7021GasSensorEventValidator implements GasSensorEventValidator {
	@Override
	public boolean isValid(byte[] event) {
		return new String(event).matches(Si7021.EVENT_REGEX);
	}
}
