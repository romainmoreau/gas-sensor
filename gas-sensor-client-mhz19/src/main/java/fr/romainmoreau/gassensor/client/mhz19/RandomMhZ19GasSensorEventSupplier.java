package fr.romainmoreau.gassensor.client.mhz19;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class RandomMhZ19GasSensorEventSupplier implements Supplier<GasSensorEvent> {
	private static final int BOUND = 5000;

	private final Random random;

	public RandomMhZ19GasSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GasSensorEvent get() {
		return new GenericGasSensorEvent(
				new GasSensing(MhZ19.CO2_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), MhZ19.CO2_UNIT));
	}
}
