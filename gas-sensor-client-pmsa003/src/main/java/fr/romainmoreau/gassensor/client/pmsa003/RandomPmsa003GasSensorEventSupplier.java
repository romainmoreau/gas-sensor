package fr.romainmoreau.gassensor.client.pmsa003;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class RandomPmsa003GasSensorEventSupplier implements Supplier<GasSensorEvent> {
	private static final int BOUND = 1000;

	private final Random random;

	public RandomPmsa003GasSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GasSensorEvent get() {
		return new GenericGasSensorEvent(
				new GasSensing(Pmsa003.PM1_0_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Pmsa003.PM_UNIT),
				new GasSensing(Pmsa003.PM2_5_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Pmsa003.PM_UNIT),
				new GasSensing(Pmsa003.PM10_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Pmsa003.PM_UNIT));
	}
}
