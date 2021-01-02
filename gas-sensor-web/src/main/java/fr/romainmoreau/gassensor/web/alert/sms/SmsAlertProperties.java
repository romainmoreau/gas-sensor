package fr.romainmoreau.gassensor.web.alert.sms;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

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
