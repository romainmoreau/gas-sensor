package fr.romainmoreau.gassensor.web.common;

import org.springframework.context.ApplicationEvent;

import fr.romainmoreau.gassensor.datamodel.GasSensingUpdate;

public class GasSensingUpdateApplicationEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private final GasSensingUpdate gasSensingUpdate;

	private final GasSensingUpdate lastGasSensingUpdate;

	public GasSensingUpdateApplicationEvent(GasSensingUpdate gasSensingUpdate, GasSensingUpdate lastGasSensingUpdate) {
		super(gasSensingUpdate);
		this.gasSensingUpdate = gasSensingUpdate;
		this.lastGasSensingUpdate = lastGasSensingUpdate;
	}

	public GasSensingUpdate getGasSensingUpdate() {
		return gasSensingUpdate;
	}

	public GasSensingUpdate getLastGasSensingUpdate() {
		return lastGasSensingUpdate;
	}
}
