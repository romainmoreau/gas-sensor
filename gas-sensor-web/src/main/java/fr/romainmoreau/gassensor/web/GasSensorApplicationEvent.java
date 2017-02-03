package fr.romainmoreau.gassensor.web;

import org.springframework.context.ApplicationEvent;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;

public class GasSensorApplicationEvent<E extends GasSensorEvent> extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private final String sensorName;

	private final E gasSensorEvent;

	public GasSensorApplicationEvent(String sensorName, E gasSensorEvent) {
		super(gasSensorEvent);
		this.sensorName = sensorName;
		this.gasSensorEvent = gasSensorEvent;
	}

	public String getSensorName() {
		return sensorName;
	}

	public E getGasSensorEvent() {
		return gasSensorEvent;
	}
}
