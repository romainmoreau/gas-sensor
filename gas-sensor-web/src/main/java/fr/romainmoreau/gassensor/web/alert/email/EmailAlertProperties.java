package fr.romainmoreau.gassensor.web.alert.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Profile("alert-email")
@ConfigurationProperties("alert.email")
public class EmailAlertProperties {
	@NotNull
	private String to;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
