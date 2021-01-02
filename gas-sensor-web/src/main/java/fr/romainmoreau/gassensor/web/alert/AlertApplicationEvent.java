package fr.romainmoreau.gassensor.web.alert;

import org.springframework.context.ApplicationEvent;

import fr.romainmoreau.gassensor.datamodel.GasSensingAlert;
import fr.romainmoreau.gassensor.datamodel.GasSensingIntervalCategory;

public class AlertApplicationEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private final GasSensingAlert gasSensingAlert;

	private final GasSensingIntervalCategory category;

	private final GasSensingIntervalCategory lastCategory;

	private final boolean on;

	public AlertApplicationEvent(GasSensingAlert gasSensingAlert, GasSensingIntervalCategory category,
			GasSensingIntervalCategory lastCategory, boolean on) {
		super(gasSensingAlert);
		this.gasSensingAlert = gasSensingAlert;
		this.category = category;
		this.lastCategory = lastCategory;
		this.on = on;
	}

	public GasSensingAlert getGasSensingAlert() {
		return gasSensingAlert;
	}

	public GasSensingIntervalCategory getCategory() {
		return category;
	}

	public GasSensingIntervalCategory getLastCategory() {
		return lastCategory;
	}

	public boolean isOn() {
		return on;
	}

	public String toShortMessage() {
		var sms = new StringBuilder();
		sms.append("GAS SENSING ");
		if (isOn()) {
			sms.append("ALERT");
		} else {
			sms.append("OK");
		}
		sms.append(": ");
		sms.append(getCategory());
		sms.append(" for ");
		sms.append(getGasSensingAlert().getSensorName());
		sms.append(" ");
		sms.append(getGasSensingAlert().getDescription());
		sms.append(" (was ");
		sms.append(getLastCategory());
		sms.append(")");
		return sms.toString();
	}
}
