package fr.romainmoreau.gassensor.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.romainmoreau.gassensor.client.common.RandomSleepMillisSupplier;
import fr.romainmoreau.gassensor.client.mhz19.MhZ19GasSensorClient;
import fr.romainmoreau.gassensor.client.mhz19.MockMhZ19GasSensorClient;
import fr.romainmoreau.gassensor.client.mhz19.RandomMhZ19GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.si7021.MockSi7021GasSensorClient;
import fr.romainmoreau.gassensor.client.si7021.RandomSi7021GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.si7021.Si7021GasSensorClient;
import fr.romainmoreau.gassensor.client.ze07.MockZe07GasSensorClient;
import fr.romainmoreau.gassensor.client.ze07.RandomZe07GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.ze07.Ze07GasSensorClient;
import fr.romainmoreau.gassensor.client.ze08.MockZe08GasSensorClient;
import fr.romainmoreau.gassensor.client.ze08.RandomZe08GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.ze08.Ze08GasSensorClient;
import fr.romainmoreau.gassensor.client.zh03a.MockZh03AGasSensorClient;
import fr.romainmoreau.gassensor.client.zh03a.RandomZh03AGasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.zh03a.Zh03AGasSensorClient;
import fr.romainmoreau.gassensor.client.zph01.MockZph01GasSensorClient;
import fr.romainmoreau.gassensor.client.zph01.RandomZph01GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.zph01.Zph01GasSensorClient;

@Profile("mock")
@Configuration
public class MockGasSensorClientConfiguration {
	private static final int MIN_SLEEP_MILLIS = 100;
	private static final int MAX_SLEEP_MILLIS = 1000;

	@Autowired
	private SpringGasSensorEventListener springGasSensorEventListener;

	@Autowired
	private Slf4JGasSensorExceptionHandler slf4JGasSensorExceptionHandler;

	@Bean
	public Ze07GasSensorClient ze07GasSensorClient() throws IOException {
		return new MockZe07GasSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomZe07GasSensorEventSupplier(), slf4JGasSensorExceptionHandler, springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}

	@Bean
	public Ze08GasSensorClient ze08GasSensorClient() throws IOException {
		return new MockZe08GasSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomZe08GasSensorEventSupplier(), slf4JGasSensorExceptionHandler, springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}

	@Bean
	public Zh03AGasSensorClient zh03AGasSensorClient() throws IOException {
		return new MockZh03AGasSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomZh03AGasSensorEventSupplier(), slf4JGasSensorExceptionHandler, springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}

	@Bean
	public Zph01GasSensorClient zph01GasSensorClient() throws IOException {
		return new MockZph01GasSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomZph01GasSensorEventSupplier(), slf4JGasSensorExceptionHandler, springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}

	@Bean
	public MhZ19GasSensorClient mhZ19GasSensorClient() throws IOException {
		return new MockMhZ19GasSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomMhZ19GasSensorEventSupplier(), slf4JGasSensorExceptionHandler, springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}

	@Bean
	public Si7021GasSensorClient si7021GasSensorClient() throws IOException {
		return new MockSi7021GasSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomSi7021GasSensorEventSupplier(), slf4JGasSensorExceptionHandler, springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}
}
