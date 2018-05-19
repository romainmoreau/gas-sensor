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
import fr.romainmoreau.gassensor.client.mhz19.JsscMhZ19GasSensorClient;
import fr.romainmoreau.gassensor.client.sds011.JsscSds011GasSensorClient;
import fr.romainmoreau.gassensor.client.si7021.JsscSi7021GasSensorClient;
import fr.romainmoreau.gassensor.client.ze07.JsscZe07GasSensorClient;
import fr.romainmoreau.gassensor.client.ze08.JsscZe08GasSensorClient;
import fr.romainmoreau.gassensor.client.zh03a.JsscZh03AGasSensorClient;
import fr.romainmoreau.gassensor.client.zph01.JsscZph01GasSensorClient;

@Profile("jssc")
@Configuration
public class JsscGasSensorClientConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsscGasSensorClientConfiguration.class);

	@Autowired
	private SpringGasSensorEventListener springGasSensorEventListener;

	@Autowired
	private Slf4JGasSensorExceptionHandler slf4JGasSensorExceptionHandler;

	@Autowired
	private Ze07JsscProperties ze07JsscProperties;

	@Autowired
	private Ze08JsscProperties ze08JsscProperties;

	@Autowired
	private Zh03AJsscProperties zh03AJsscProperties;

	@Autowired
	private Zph01JsscProperties zph01JsscProperties;

	@Autowired
	private MhZ19JsscProperties mhZ19JsscProperties;

	@Autowired
	private Si7021JsscProperties si7021JsscProperties;

	@Autowired
	private Sds011JsscProperties sds011JsscProperties;

	private List<GasSensorClient<?>> gasSensorClientList;

	public JsscGasSensorClientConfiguration() {
		gasSensorClientList = new ArrayList<>();
	}

	@PostConstruct
	private void postConstruct() throws IOException {
		if (ze07JsscProperties.getGasSensors() != null) {
			for (JsscGasSensor jsscGasSensor : ze07JsscProperties.getGasSensors()) {
				gasSensorClientList.add(new JsscZe07GasSensorClient(jsscGasSensor.getDescription(),
						jsscGasSensor.getPortName(), springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			}
		}
		if (ze08JsscProperties.getGasSensors() != null) {
			for (JsscGasSensor jsscGasSensor : ze08JsscProperties.getGasSensors()) {
				gasSensorClientList.add(new JsscZe08GasSensorClient(jsscGasSensor.getDescription(),
						jsscGasSensor.getPortName(), springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			}
		}
		if (zh03AJsscProperties.getGasSensors() != null) {
			for (JsscGasSensor jsscGasSensor : zh03AJsscProperties.getGasSensors()) {
				gasSensorClientList.add(new JsscZh03AGasSensorClient(jsscGasSensor.getDescription(),
						jsscGasSensor.getPortName(), springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			}
		}
		if (zph01JsscProperties.getGasSensors() != null) {
			for (JsscGasSensor jsscGasSensor : zph01JsscProperties.getGasSensors()) {
				gasSensorClientList.add(new JsscZph01GasSensorClient(jsscGasSensor.getDescription(),
						jsscGasSensor.getPortName(), springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			}
		}
		if (mhZ19JsscProperties.getGasSensors() != null) {
			for (JsscGasSensor jsscGasSensor : mhZ19JsscProperties.getGasSensors()) {
				gasSensorClientList.add(new JsscMhZ19GasSensorClient(jsscGasSensor.getDescription(),
						jsscGasSensor.getPortName(), springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			}
		}
		if (si7021JsscProperties.getGasSensors() != null) {
			for (JsscGasSensor jsscGasSensor : si7021JsscProperties.getGasSensors()) {
				gasSensorClientList.add(new JsscSi7021GasSensorClient(jsscGasSensor.getDescription(),
						jsscGasSensor.getPortName(), springGasSensorEventListener, slf4JGasSensorExceptionHandler));
			}
		}
		if (sds011JsscProperties.getGasSensors() != null) {
			for (JsscGasSensor jsscGasSensor : sds011JsscProperties.getGasSensors()) {
				gasSensorClientList.add(new JsscSds011GasSensorClient(jsscGasSensor.getDescription(),
						jsscGasSensor.getPortName(), springGasSensorEventListener, slf4JGasSensorExceptionHandler));
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
