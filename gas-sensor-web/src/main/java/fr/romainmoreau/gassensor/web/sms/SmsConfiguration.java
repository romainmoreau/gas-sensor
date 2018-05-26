package fr.romainmoreau.gassensor.web.sms;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.romainmoreau.gsmmodem.client.api.GsmModemClient;
import fr.romainmoreau.gsmmodem.client.web.WebGsmModemClient;

@Configuration
@Profile("sms")
public class SmsConfiguration {
	@Autowired
	private GsmModemProperties gsmModemProperties;

	@Bean
	public GsmModemClient gsmModemClient() throws MalformedURLException, URISyntaxException {
		return new WebGsmModemClient(gsmModemProperties.getProtocol(), gsmModemProperties.getHost(),
				gsmModemProperties.getPort());
	}
}
