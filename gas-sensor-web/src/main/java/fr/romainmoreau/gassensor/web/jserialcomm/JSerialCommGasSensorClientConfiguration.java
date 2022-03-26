package fr.romainmoreau.gassensor.web.jserialcomm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.mhz19.JSerialCommMhZ19GasSensorClient;
import fr.romainmoreau.gassensor.client.pmsa003.JSerialCommPmsa003GasSensorClient;
import fr.romainmoreau.gassensor.client.sds011.JSerialCommSds011GasSensorClient;
import fr.romainmoreau.gassensor.client.sds018.JSerialCommSds018GasSensorClient;
import fr.romainmoreau.gassensor.client.si7021.JSerialCommSi7021GasSensorClient;
import fr.romainmoreau.gassensor.client.ze07.JSerialCommZe07GasSensorClient;
import fr.romainmoreau.gassensor.client.ze08.JSerialCommZe08GasSensorClient;
import fr.romainmoreau.gassensor.client.zh03a.JSerialCommZh03AGasSensorClient;
import fr.romainmoreau.gassensor.client.zph01.JSerialCommZph01GasSensorClient;
import fr.romainmoreau.gassensor.web.common.Slf4JGasSensorExceptionHandler;
import fr.romainmoreau.gassensor.web.common.SpringGasSensorEventListener;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Profile("jserialcomm")
@Configuration
public class JSerialCommGasSensorClientConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(JSerialCommGasSensorClientConfiguration.class);

	@Autowired
	private SpringGasSensorEventListener springGasSensorEventListener;

	@Autowired
	private Slf4JGasSensorExceptionHandler slf4JGasSensorExceptionHandler;

	@Autowired
	private Ze07JSerialCommProperties ze07JSerialCommProperties;

	@Autowired
	private Ze08JSerialCommProperties ze08JSerialCommProperties;

	@Autowired
	private Zh03AJSerialCommProperties zh03AJSerialCommProperties;

	@Autowired
	private Zph01JSerialCommProperties zph01JSerialCommProperties;

	@Autowired
	private MhZ19JSerialCommProperties mhZ19JSerialCommProperties;

	@Autowired
	private Si7021JSerialCommProperties si7021JSerialCommProperties;

	@Autowired
	private Sds011JSerialCommProperties sds011JSerialCommProperties;

	@Autowired
	private Sds018JSerialCommProperties sds018JSerialCommProperties;

	@Autowired
	private Pmsa003JSerialCommProperties pmsa003JSerialCommProperties;

	private List<GasSensorClient<?>> gasSensorClientList;

	public JSerialCommGasSensorClientConfiguration() {
		gasSensorClientList = new ArrayList<>();
	}

	@PostConstruct
	private void postConstruct() throws IOException {
		if (ze07JSerialCommProperties.getGasSensors() != null) {
			for (JSerialCommGasSensor jSerialCommGasSensor : ze07JSerialCommProperties.getGasSensors()) {
				gasSensorClientList.add(new JSerialCommZe07GasSensorClient(jSerialCommGasSensor.getDescription(),
						jSerialCommGasSensor.getPortName(), springGasSensorEventListener,
						slf4JGasSensorExceptionHandler));
			}
		}
		if (ze08JSerialCommProperties.getGasSensors() != null) {
			for (JSerialCommGasSensor jSerialCommGasSensor : ze08JSerialCommProperties.getGasSensors()) {
				gasSensorClientList.add(new JSerialCommZe08GasSensorClient(jSerialCommGasSensor.getDescription(),
						jSerialCommGasSensor.getPortName(), springGasSensorEventListener,
						slf4JGasSensorExceptionHandler));
			}
		}
		if (zh03AJSerialCommProperties.getGasSensors() != null) {
			for (JSerialCommGasSensor jSerialCommGasSensor : zh03AJSerialCommProperties.getGasSensors()) {
				gasSensorClientList.add(new JSerialCommZh03AGasSensorClient(jSerialCommGasSensor.getDescription(),
						jSerialCommGasSensor.getPortName(), springGasSensorEventListener,
						slf4JGasSensorExceptionHandler));
			}
		}
		if (zph01JSerialCommProperties.getGasSensors() != null) {
			for (JSerialCommGasSensor jSerialCommGasSensor : zph01JSerialCommProperties.getGasSensors()) {
				gasSensorClientList.add(new JSerialCommZph01GasSensorClient(jSerialCommGasSensor.getDescription(),
						jSerialCommGasSensor.getPortName(), springGasSensorEventListener,
						slf4JGasSensorExceptionHandler));
			}
		}
		if (mhZ19JSerialCommProperties.getGasSensors() != null) {
			for (JSerialCommGasSensor jSerialCommGasSensor : mhZ19JSerialCommProperties.getGasSensors()) {
				gasSensorClientList.add(new JSerialCommMhZ19GasSensorClient(jSerialCommGasSensor.getDescription(),
						jSerialCommGasSensor.getPortName(), springGasSensorEventListener,
						slf4JGasSensorExceptionHandler));
			}
		}
		if (si7021JSerialCommProperties.getGasSensors() != null) {
			for (JSerialCommGasSensor jSerialCommGasSensor : si7021JSerialCommProperties.getGasSensors()) {
				gasSensorClientList.add(new JSerialCommSi7021GasSensorClient(jSerialCommGasSensor.getDescription(),
						jSerialCommGasSensor.getPortName(), springGasSensorEventListener,
						slf4JGasSensorExceptionHandler));
			}
		}
		if (sds011JSerialCommProperties.getGasSensors() != null) {
			for (JSerialCommGasSensor jSerialCommGasSensor : sds011JSerialCommProperties.getGasSensors()) {
				gasSensorClientList.add(new JSerialCommSds011GasSensorClient(jSerialCommGasSensor.getDescription(),
						jSerialCommGasSensor.getPortName(), springGasSensorEventListener,
						slf4JGasSensorExceptionHandler));
			}
		}
		if (sds018JSerialCommProperties.getGasSensors() != null) {
			for (JSerialCommGasSensor jSerialCommGasSensor : sds018JSerialCommProperties.getGasSensors()) {
				gasSensorClientList.add(new JSerialCommSds018GasSensorClient(jSerialCommGasSensor.getDescription(),
						jSerialCommGasSensor.getPortName(), springGasSensorEventListener,
						slf4JGasSensorExceptionHandler));
			}
		}
		if (pmsa003JSerialCommProperties.getGasSensors() != null) {
			for (JSerialCommGasSensor jSerialCommGasSensor : pmsa003JSerialCommProperties.getGasSensors()) {
				gasSensorClientList.add(new JSerialCommPmsa003GasSensorClient(jSerialCommGasSensor.getDescription(),
						jSerialCommGasSensor.getPortName(), springGasSensorEventListener,
						slf4JGasSensorExceptionHandler));
			}
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
