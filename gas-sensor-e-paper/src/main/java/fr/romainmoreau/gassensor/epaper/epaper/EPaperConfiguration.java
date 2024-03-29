package fr.romainmoreau.gassensor.epaper.epaper;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.jserialcomm.JSerialCommEPaperClient;

@Configuration
public class EPaperConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(EPaperConfiguration.class);

	@Autowired
	private EPaperProperties ePaperProperties;

	@Bean
	EPaperClient ePaperClient() throws IOException {
		LOGGER.info("Creating jSerialComm e-paper client using port name {}, timeout {} and receive timeout {}",
				ePaperProperties.getPortName(), ePaperProperties.getTimeout(), ePaperProperties.getReceiveTimeout());
		return new JSerialCommEPaperClient(ePaperProperties.getPortName(), ePaperProperties.getTimeout(),
				ePaperProperties.getReceiveTimeout());
	}
}
