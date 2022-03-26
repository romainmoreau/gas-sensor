package fr.romainmoreau.gassensor.web.alert.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import jakarta.validation.constraints.NotNull;

@Profile("alert-sms")
@ConfigurationProperties("alert.sms")
public class SmsAlertProperties {
	@NotNull
	private String gsmNumber;

	public String getGsmNumber() {
		return gsmNumber;
	}

	public void setGsmNumber(String gsmNumber) {
		this.gsmNumber = gsmNumber;
	}
}
