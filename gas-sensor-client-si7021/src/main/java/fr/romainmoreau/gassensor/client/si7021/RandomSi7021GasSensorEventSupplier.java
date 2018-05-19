package fr.romainmoreau.gassensor.client.si7021;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class RandomSi7021GasSensorEventSupplier implements Supplier<GasSensorEvent> {
	private static final int TEMPERATURE_BOUND = 1000;
	private static final int HUMIDITY_BOUND = 1000;

	private final Random random;

	public RandomSi7021GasSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GasSensorEvent get() {
		return new GenericGasSensorEvent(new GasSensing(Si7021.TEMPERATURE_DESCRIPTION,
				new BigDecimal(random.nextInt(TEMPERATURE_BOUND)).scaleByPowerOfTen(-1), Si7021.TEMPERATURE_UNIT),
				new GasSensing(Si7021.HUMIDITY_DESCRIPTION,
						new BigDecimal(random.nextInt(HUMIDITY_BOUND)).scaleByPowerOfTen(-1), Si7021.HUMIDITY_UNIT));
	}
}
