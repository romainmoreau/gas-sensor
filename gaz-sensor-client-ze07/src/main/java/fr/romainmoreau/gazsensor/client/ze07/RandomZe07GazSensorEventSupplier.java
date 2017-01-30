package fr.romainmoreau.gazsensor.client.ze07;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GenericGazSensorEvent;

public class RandomZe07GazSensorEventSupplier implements Supplier<GazSensorEvent> {
	private static final int BOUND = 5000;

	private final Random random;

	public RandomZe07GazSensorEventSupplier() {
		random = new SecureRandom();
	}

	@Override
	public GazSensorEvent get() {
		return new GenericGazSensorEvent(new GazSensing(Ze07.CO_DESCRIPTION,
				new BigDecimal(random.nextInt(BOUND)).multiply(Ze07.CO_MULTIPLICAND), Ze07.CO_UNIT));
	}
}
