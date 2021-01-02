package fr.romainmoreau.gassensor.web.alert;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

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
