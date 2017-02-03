package fr.romainmoreau.gassensor.client.zh03a;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class RandomZh03AGasSensorEventSupplier implements Supplier<GasSensorEvent> {
	private static final int BOUND = 1000;

	private final Random random;

	public RandomZh03AGasSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GasSensorEvent get() {
		return new GenericGasSensorEvent(
				new GasSensing(Zh03A.PM1_0_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Zh03A.PM_UNIT),
				new GasSensing(Zh03A.PM2_5_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Zh03A.PM_UNIT),
				new GasSensing(Zh03A.PM10_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Zh03A.PM_UNIT));
	}
}
