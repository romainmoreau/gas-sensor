package fr.romainmoreau.gazsensor.web;

import org.springframework.context.ApplicationEvent;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;

public class GazSensorApplicationEvent<E extends GazSensorEvent> extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private final String sensorName;

	private final E gazSensorEvent;

	public GazSensorApplicationEvent(String sensorName, E gazSensorEvent) {
		super(gazSensorEvent);
		this.sensorName = sensorName;
		this.gazSensorEvent = gazSensorEvent;
	}

	public String getSensorName() {
		return sensorName;
	}

	public E getGazSensorEvent() {
		return gazSensorEvent;
	}
}
