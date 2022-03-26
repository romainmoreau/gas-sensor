package fr.romainmoreau.gassensor.epaper.gassensing;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Validated
@ConfigurationProperties("gas-sensing")
public class GasSensingProperties {
	@NotNull
	private String baseUrl;

	@Min(0)
	private int maxMilliseconds;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public int getMaxMilliseconds() {
		return maxMilliseconds;
	}

	public void setMaxMilliseconds(int maxMilliseconds) {
		this.maxMilliseconds = maxMilliseconds;
	}
}
