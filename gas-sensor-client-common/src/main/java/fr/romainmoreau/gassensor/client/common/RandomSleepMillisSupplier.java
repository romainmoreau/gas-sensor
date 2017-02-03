package fr.romainmoreau.gassensor.client.common;

import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

public class RandomSleepMillisSupplier implements Supplier<Long> {
	private final int min;

	private final int max;

	private final Random random;

	public RandomSleepMillisSupplier(int min, int max) {
		this.min = min;
		this.max = max;
		this.random = new SecureRandom();
	}

	@Override
	public Long get() {
		return (long) (min + random.nextInt(max - min));
	}
}
