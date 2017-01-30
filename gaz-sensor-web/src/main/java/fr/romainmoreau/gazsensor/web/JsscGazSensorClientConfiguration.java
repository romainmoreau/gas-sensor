package fr.romainmoreau.gazsensor.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${ze07.port-name}")
	private String ze07PortName;

	@Value("${ze08.port-name}")
	private String ze08PortName;

	@Value("${zh03a.port-name}")
	private String zh03aPortName;

	@Value("${zph01.port-name}")
	private String zph01PortName;

	@Bean
	public Ze07GazSensorClient ze07GazSensorClient() throws IOException {
		return new JsscZe07GazSensorClient(ze07PortName, springGazSensorEventListener, slf4JGazSensorExceptionHandler);
	}

	@Bean
	public Ze08GazSensorClient ze08GazSensorClient() throws IOException {
		return new JsscZe08GazSensorClient(ze08PortName, springGazSensorEventListener, slf4JGazSensorExceptionHandler);
	}

	@Bean
	public Zh03AGazSensorClient zh03aGazSensorClient() throws IOException {
		return new JsscZh03AGazSensorClient(zh03aPortName, springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}

	@Bean
	public Zph01GazSensorClient zph01GazSensorClient() throws IOException {
		return new JsscZph01GazSensorClient(zph01PortName, springGazSensorEventListener,
				slf4JGazSensorExceptionHandler);
	}
}
