package fr.romainmoreau.gassensor.epaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan
public class GasSensorEPaperApplication {
	public static final void main(String[] args) {
		SpringApplication.run(GasSensorEPaperApplication.class, args);
	}
}
