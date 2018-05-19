package fr.romainmoreau.gassensor.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.common.RandomSleepMillisSupplier;
import fr.romainmoreau.gassensor.client.mhz19.MockMhZ19GasSensorClient;
import fr.romainmoreau.gassensor.client.mhz19.RandomMhZ19GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.sds011.MockSds011GasSensorClient;
import fr.romainmoreau.gassensor.client.sds011.RandomSds011GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.si7021.MockSi7021GasSensorClient;
import fr.romainmoreau.gassensor.client.si7021.RandomSi7021GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.ze07.MockZe07GasSensorClient;
import fr.romainmoreau.gassensor.client.ze07.RandomZe07GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.ze08.MockZe08GasSensorClient;
import fr.romainmoreau.gassensor.client.ze08.RandomZe08GasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.zh03a.MockZh03AGasSensorClient;
import fr.romainmoreau.gassensor.client.zh03a.RandomZh03AGasSensorEventSupplier;
import fr.romainmoreau.gassensor.client.zph01.MockZph01GasSensorClient;
import fr.romainmoreau.gassensor.client.zph01.RandomZph01GasSensorEventSupplier;

@Profile("mock")
@Configuration
public class MockGasSensorClientConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(MockGasSensorClientConfiguration.class);

	private static final int GAS_SENSOR_COUNT = 2;
	private static final int MIN_SLEEP_MILLIS = 100;
	private static final int MAX_SLEEP_MILLIS = 1000;

	@Autowired
	private SpringGasSensorEventListener springGasSensorEventListener;

	@Autowired
	private Slf4JGasSensorExceptionHandler slf4JGasSensorExceptionHandler;

	private List<GasSensorClient<?>> gasSensorClientList;

	public MockGasSensorClientConfiguration() {
		gasSensorClientList = new ArrayList<>();
	}

	@PostConstruct
	private void postConstruct() throws IOException {
		for (int i = 0; i < GAS_SENSOR_COUNT; i++) {
			String description = "_" + Integer.toString(i + 1);
			gasSensorClientList.add(new MockZe07GasSensorClient(description,
					new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
					new RandomZe07GasSensorEventSupplier(), slf4JGasSensorExceptionHandler,
					springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			gasSensorClientList.add(new MockZe08GasSensorClient(description,
					new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
					new RandomZe08GasSensorEventSupplier(), slf4JGasSensorExceptionHandler,
					springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			gasSensorClientList.add(new MockZh03AGasSensorClient(description,
					new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
					new RandomZh03AGasSensorEventSupplier(), slf4JGasSensorExceptionHandler,
					springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			gasSensorClientList.add(new MockZph01GasSensorClient(description,
					new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
					new RandomZph01GasSensorEventSupplier(), slf4JGasSensorExceptionHandler,
					springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			gasSensorClientList.add(new MockMhZ19GasSensorClient(description,
					new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
					new RandomMhZ19GasSensorEventSupplier(), slf4JGasSensorExceptionHandler,
					springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			gasSensorClientList.add(new MockSi7021GasSensorClient(description,
					new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
					new RandomSi7021GasSensorEventSupplier(), slf4JGasSensorExceptionHandler,
					springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			gasSensorClientList.add(new MockSds011GasSensorClient(description,
					new RandomSleepMillisSupplier(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS),
					new RandomSds011GasSensorEventSupplier(), slf4JGasSensorExceptionHandler,
					springGasSensorEventListener, slf4JGasSensorExceptionHandler));
		}
	}

	@PreDestroy
	private void preDestroy() {
		for (GasSensorClient<?> gasSensorClient : gasSensorClientList) {
			try {
				gasSensorClient.close();
			} catch (IOException e) {
				LOGGER.error("Exception while closing gas sensor client", e);
			}
		}
	}
}
