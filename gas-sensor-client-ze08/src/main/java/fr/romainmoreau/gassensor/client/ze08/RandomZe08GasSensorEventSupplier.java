package fr.romainmoreau.gassensor.client.ze08;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class RandomZe08GasSensorEventSupplier implements Supplier<GasSensorEvent> {
	private static final int BOUND = 5000;

	private final Random random;

	public RandomZe08GasSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GasSensorEvent get() {
		return new GenericGasSensorEvent(
				new GasSensing(Ze08.CH2O_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Ze08.CH2O_UNIT));
	}
}
