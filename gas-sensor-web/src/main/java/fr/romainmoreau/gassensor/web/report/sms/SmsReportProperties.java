package fr.romainmoreau.gassensor.web.report.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import jakarta.validation.constraints.NotNull;

@Profile("report-sms")
@ConfigurationProperties("report.sms")
public class SmsReportProperties {
	@NotNull
	private String gsmNumber;

	public String getGsmNumber() {
		return gsmNumber;
	}

	public void setGsmNumber(String gsmNumber) {
		this.gsmNumber = gsmNumber;
	}
}
