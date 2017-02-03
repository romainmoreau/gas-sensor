package fr.romainmoreau.gassensor.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.romainmoreau.gassensor.client.ze07.JsscZe07GasSensorClient;
import fr.romainmoreau.gassensor.client.ze07.Ze07GasSensorClient;
import fr.romainmoreau.gassensor.client.ze08.JsscZe08GasSensorClient;
import fr.romainmoreau.gassensor.client.ze08.Ze08GasSensorClient;
import fr.romainmoreau.gassensor.client.zh03a.JsscZh03AGasSensorClient;
import fr.romainmoreau.gassensor.client.zh03a.Zh03AGasSensorClient;
import fr.romainmoreau.gassensor.client.zph01.JsscZph01GasSensorClient;
import fr.romainmoreau.gassensor.client.zph01.Zph01GasSensorClient;

@Profile("jssc")
@Configuration
public class JsscGasSensorClientConfiguration {
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

	@Bean
	@ConditionalOnProperty("ze07.port-name")
	public Ze07GasSensorClient ze07GasSensorClient() throws IOException {
		return new JsscZe07GasSensorClient(ze07JsscProperties.getPortName(), springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}

	@Bean
	@ConditionalOnProperty("ze08.port-name")
	public Ze08GasSensorClient ze08GasSensorClient() throws IOException {
		return new JsscZe08GasSensorClient(ze08JsscProperties.getPortName(), springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}

	@Bean
	@ConditionalOnProperty("zh03a.port-name")
	public Zh03AGasSensorClient zh03aGasSensorClient() throws IOException {
		return new JsscZh03AGasSensorClient(zh03AJsscProperties.getPortName(), springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}

	@Bean
	@ConditionalOnProperty("zph01.port-name")
	public Zph01GasSensorClient zph01GasSensorClient() throws IOException {
		return new JsscZph01GasSensorClient(zph01JsscProperties.getPortName(), springGasSensorEventListener,
				slf4JGasSensorExceptionHandler);
	}
}
