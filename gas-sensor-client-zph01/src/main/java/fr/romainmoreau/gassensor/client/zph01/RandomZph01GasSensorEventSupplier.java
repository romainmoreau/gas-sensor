package fr.romainmoreau.gassensor.client.zph01;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class RandomZph01GasSensorEventSupplier implements Supplier<GasSensorEvent> {
	private static final int VOC_BOUND = 4;
	private static final int PM_BOUND = 10000;

	private final Random random;

	public RandomZph01GasSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GasSensorEvent get() {
		return new GenericGasSensorEvent(
				new GasSensing(Zph01.PM2_5_DESCRIPTION,
						new BigDecimal(random.nextInt(PM_BOUND)).divide(Zph01.PM2_5_DIVISOR), Zph01.PM2_5_UNIT),
				new GasSensing(Zph01.VOC_DESCRIPTION, new BigDecimal(random.nextInt(VOC_BOUND)), Zph01.VOC_UNIT));
	}
}
