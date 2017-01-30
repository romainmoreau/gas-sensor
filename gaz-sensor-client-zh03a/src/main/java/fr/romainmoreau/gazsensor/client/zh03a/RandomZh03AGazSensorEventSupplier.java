package fr.romainmoreau.gazsensor.client.zh03a;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GenericGazSensorEvent;

public class RandomZh03AGazSensorEventSupplier implements Supplier<GazSensorEvent> {
	private static final int BOUND = 1000;

	private final Random random;

	public RandomZh03AGazSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GazSensorEvent get() {
		return new GenericGazSensorEvent(
				new GazSensing(Zh03A.PM1_0_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Zh03A.PM_UNIT),
				new GazSensing(Zh03A.PM2_5_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Zh03A.PM_UNIT),
				new GazSensing(Zh03A.PM10_DESCRIPTION, new BigDecimal(random.nextInt(BOUND)), Zh03A.PM_UNIT));
	}
}
