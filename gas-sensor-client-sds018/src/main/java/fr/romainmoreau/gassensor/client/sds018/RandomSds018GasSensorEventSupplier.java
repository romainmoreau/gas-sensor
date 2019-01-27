package fr.romainmoreau.gassensor.client.sds018;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class RandomSds018GasSensorEventSupplier implements Supplier<GasSensorEvent> {
	private static final int BOUND = 1000;

	private final Random random;

	public RandomSds018GasSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GasSensorEvent get() {
		return new GenericGasSensorEvent(
				new GasSensing(Sds018.PM2_5_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Sds018.PM_UNIT),
				new GasSensing(Sds018.PM10_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Sds018.PM_UNIT));
	}
}
