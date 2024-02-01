package fr.romainmoreau.gassensor.web.report.email;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.web.report.GasSensingReport;
import fr.romainmoreau.gassensor.web.report.ReportApplicationEvent;

@Component
@Profile("report-email")
public class EmailReportEventListener {
	private static final NumberFormat PERCENT_DECIMAL_FORMAT = new DecimalFormat("0.## %");

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailReportProperties emailReportProperties;

	@Async
	@EventListener(ReportApplicationEvent.class)
	public void onReportApplicationEvent(ReportApplicationEvent reportApplicationEvent) {
		var simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(emailReportProperties.getTo());
		simpleMailMessage.setSubject(reportApplicationEvent.getDay() + " gas sensing report");
		simpleMailMessage.setText(reportApplicationEvent.getGasSensingReports().stream().map(report -> getText(report))
				.collect(Collectors.joining("\n")));
		javaMailSender.send(simpleMailMessage);
	}

	private String getText(GasSensingReport gasSensingReport) {
		var stringBuilder = new StringBuilder();
		stringBuilder.append(gasSensingReport.getDescription());
		stringBuilder.append(" (");
		stringBuilder.append(gasSensingReport.getUnit());
		stringBuilder.append(") (");
		stringBuilder.append(gasSensingReport.getSensorName());
		stringBuilder.append(")\n");
		Optional.of(gasSensingReport.getSeverePercent())
				.ifPresent(severePercent -> appendPercent("Severe percent", severePercent, stringBuilder));
		Optional.of(gasSensingReport.getWarningPercent())
				.ifPresent(warningPercent -> appendPercent("Warning percent", warningPercent, stringBuilder));
		Optional.of(gasSensingReport.getFinePercent())
				.ifPresent(finePercent -> appendPercent("Fine percent", finePercent, stringBuilder));
		Optional.of(gasSensingReport.getLongestMillisWithoutUpdate())
				.ifPresent(longestMillisWithoutUpdate -> {
					stringBuilder.append("Longest duration without update: ");
					stringBuilder
							.append(DurationFormatUtils.formatDurationWords(longestMillisWithoutUpdate, true, true));
					stringBuilder.append("\n");
				});
		return stringBuilder.toString();
	}

	private void appendPercent(String name, double percent, StringBuilder stringBuilder) {
		stringBuilder.append(name);
		stringBuilder.append(": ");
		stringBuilder.append(PERCENT_DECIMAL_FORMAT.format(percent));
		stringBuilder.append("\n");
	}
}
