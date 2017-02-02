package fr.romainmoreau.gazsensor.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.romainmoreau.gazsensor.client.ze07.JsscZe07GazSensorClient;
import fr.romainmoreau.gazsensor.client.ze07.Ze07GazSensorClient;
import fr.romainmoreau.gazsensor.client.ze08.JsscZe08GazSensorClient;
import fr.romainmoreau.gazsensor.client.ze08.Ze08GazSensorClient;
import fr.romainmoreau.gazsensor.client.zh03a.JsscZh03AGazSensorClient;
import fr.romainmoreau.gazsensor.client.zh03a.Zh03AGazSensorClient;
import fr.romainmoreau.gazsensor.client.zph01.JsscZph01GazSensorClient;
import fr.romainmoreau.gazsensor.client.zph01.Zph01GazSensorClient;

@Profile("jssc")
@Configuration
public class JsscGazSensorClientConfiguration {
	@Autowired
	private SpringGazSensorEventListener springGazSensorEventListener;

	@Autowired
	private Slf4JGazSensorExceptionHandler slf4JGazSensorExceptionHandler;

	@Autowired
	private Ze07JsscProperties ze07JsscProperties;

	@Autowired
	private Ze08JsscProperties ze08JsscProperties;

	@Autowired
	private Zh03AJsscProperties zh03AJsscProperties;

	@Autowired
	private Zph01JsscProperties zph01JsscProperties;

	@Bean
	@ConditionalOnProperty("ze07.port-name")
	public Ze07GazSensorClient ze07GazSensorClient() throws IOException {
		return new JsscZe07GazSensorClient(ze07JsscProperties.getPortName(), springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}

	@Bean
	@ConditionalOnProperty("ze08.port-name")
	public Ze08GazSensorClient ze08GazSensorClient() throws IOException {
		return new JsscZe08GazSensorClient(ze08JsscProperties.getPortName(), springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}

	@Bean
	@ConditionalOnProperty("zh03a.port-name")
	public Zh03AGazSensorClient zh03aGazSensorClient() throws IOException {
		return new JsscZh03AGazSensorClient(zh03AJsscProperties.getPortName(), springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}

	@Bean
	@ConditionalOnProperty("zph01.port-name")
	public Zph01GazSensorClient zph01GazSensorClient() throws IOException {
		return new JsscZph01GazSensorClient(zph01JsscProperties.getPortName(), springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}
}
