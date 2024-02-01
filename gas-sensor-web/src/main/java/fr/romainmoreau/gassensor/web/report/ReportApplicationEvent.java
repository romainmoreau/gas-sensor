package fr.romainmoreau.gassensor.web.report;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.context.ApplicationEvent;

public class ReportApplicationEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private final Collection<GasSensingReport> gasSensingReports;

	private final LocalDate day;

	public ReportApplicationEvent(Collection<GasSensingReport> gasSensingReports, LocalDate day) {
		super(gasSensingReports);
		this.gasSensingReports = gasSensingReports;
		this.day = day;
	}

	public Collection<GasSensingReport> getGasSensingReports() {
		return gasSensingReports;
	}

	public LocalDate getDay() {
		return day;
	}
}
