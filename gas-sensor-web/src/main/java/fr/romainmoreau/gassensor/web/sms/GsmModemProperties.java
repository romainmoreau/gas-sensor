package fr.romainmoreau.gassensor.web.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Profile("sms")
@ConfigurationProperties("gsm-modem")
public class GsmModemProperties {
	@NotNull
	private String protocol;

	@NotNull
	private String host;

	@Min(1)
	private int port;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
