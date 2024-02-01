package fr.romainmoreau.gassensor.web.report.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import jakarta.validation.constraints.NotNull;

@Profile("report-email")
@ConfigurationProperties("report.email")
public class EmailReportProperties {
	@NotNull
	private String to;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
