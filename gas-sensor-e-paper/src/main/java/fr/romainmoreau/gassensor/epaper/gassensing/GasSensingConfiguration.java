package fr.romainmoreau.gassensor.epaper.gassensing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GasSensingConfiguration {
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
