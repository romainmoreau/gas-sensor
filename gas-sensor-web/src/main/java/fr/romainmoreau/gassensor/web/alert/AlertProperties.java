package fr.romainmoreau.gassensor.web.alert;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Profile("alert")
@ConfigurationProperties("alert")
public class AlertProperties {
	@NotNull
	@Min(60000)
	private int debounceMillis;

	public int getDebounceMillis() {
		return debounceMillis;
	}

	public void setDebounceMillis(int debounceMillis) {
		this.debounceMillis = debounceMillis;
	}
}
