package fr.romainmoreau.gazsensor.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.romainmoreau.gazsensor.client.common.RandomSleepMillisSupplier;
import fr.romainmoreau.gazsensor.client.ze07.MockZe07GazSensorClient;
import fr.romainmoreau.gazsensor.client.ze07.RandomZe07GazSensorEventSupplier;
import fr.romainmoreau.gazsensor.client.ze07.Ze07GazSensorClient;
import fr.romainmoreau.gazsensor.client.ze08.MockZe08GazSensorClient;
import fr.romainmoreau.gazsensor.client.ze08.RandomZe08GazSensorEventSupplier;
import fr.romainmoreau.gazsensor.client.ze08.Ze08GazSensorClient;
import fr.romainmoreau.gazsensor.client.zh03a.MockZh03AGazSensorClient;
import fr.romainmoreau.gazsensor.client.zh03a.RandomZh03AGazSensorEventSupplier;
import fr.romainmoreau.gazsensor.client.zh03a.Zh03AGazSensorClient;
import fr.romainmoreau.gazsensor.client.zph01.MockZph01GazSensorClient;
import fr.romainmoreau.gazsensor.client.zph01.RandomZph01GazSensorEventSupplier;
import fr.romainmoreau.gazsensor.client.zph01.Zph01GazSensorClient;

@Profile("mock")
@Configuration
public class MockGazSensorClientConfiguration {
	private static final int MIN_SLEEP_MILLIS = 100;
	private static final int MAX_SLEEP_MILLIS = 1000;

	@Autowired
	private SpringGazSensorEventListener springGazSensorEventListener;

	@Autowired
	private Slf4JGazSensorExceptionHandler slf4JGazSensorExceptionHandler;

	@Bean
	public Ze07GazSensorClient ze07GazSensorClient() throws IOException {
		return new MockZe07GazSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomZe07GazSensorEventSupplier(), slf4JGazSensorExceptionHandler, springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}

	@Bean
	public Ze08GazSensorClient ze08GazSensorClient() throws IOException {
		return new MockZe08GazSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomZe08GazSensorEventSupplier(), slf4JGazSensorExceptionHandler, springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}

	@Bean
	public Zh03AGazSensorClient zh03AGazSensorClient() throws IOException {
		return new MockZh03AGazSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomZh03AGazSensorEventSupplier(), slf4JGazSensorExceptionHandler, springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}

	@Bean
	public Zph01GazSensorClient zph01GazSensorClient() throws IOException {
		return new MockZph01GazSensorClient(new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
				new RandomZph01GazSensorEventSupplier(), slf4JGazSensorExceptionHandler, springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}
}
