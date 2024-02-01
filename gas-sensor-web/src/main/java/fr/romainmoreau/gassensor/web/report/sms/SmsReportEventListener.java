package fr.romainmoreau.gassensor.web.report.sms;

import java.util.Objects;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.web.report.GasSensingReport;
import fr.romainmoreau.gassensor.web.report.ReportApplicationEvent;
import fr.romainmoreau.gsmmodem.client.api.GsmModemClient;

@Component
@Profile("report-sms")
public class SmsReportEventListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(SmsReportEventListener.class);

	@Autowired
	private GsmModemClient gsmModemClient;

	@Autowired
	private SmsReportProperties smsReportProperties;

	@Async
	@EventListener(ReportApplicationEvent.class)
	public void onReportApplicationEvent(ReportApplicationEvent reportApplicationEvent) {
		try {
			gsmModemClient
					.sendSms(smsReportProperties.getGsmNumber(),
							reportApplicationEvent.getGasSensingReports().stream()
									.map(GasSensingReport::getLongestMillisWithoutUpdate).filter(Objects::nonNull)
									.max(Long::compareTo)
									.map(m -> "Worst sensor duration without update: "
											+ DurationFormatUtils.formatDurationWords(m, true, true))
									.orElse("No data found"));
		} catch (Exception e) {
			LOGGER.error("Exception while sending SMS report", e);
		}
	}
}
