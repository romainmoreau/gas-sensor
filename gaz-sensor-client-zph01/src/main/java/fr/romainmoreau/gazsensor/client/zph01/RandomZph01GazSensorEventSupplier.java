package fr.romainmoreau.gazsensor.client.zph01;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GenericGazSensorEvent;

public class RandomZph01GazSensorEventSupplier implements Supplier<GazSensorEvent> {
	private static final int VOC_BOUND = 4;
	private static final int PM_BOUND = 10000;

	private final Random random;

	public RandomZph01GazSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GazSensorEvent get() {
		return new GenericGazSensorEvent(
				new GazSensing(Zph01.PM2_5_DESCRIPTION,
						new BigDecimal(random.nextInt(PM_BOUND)).divide(Zph01.PM2_5_DIVISOR), Zph01.PM2_5_UNIT),
				new GazSensing(Zph01.VOC_DESCRIPTION, new BigDecimal(random.nextInt(VOC_BOUND)), Zph01.VOC_UNIT));
	}
}
