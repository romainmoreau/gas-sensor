package fr.romainmoreau.gazsensor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;

@Component
public class SpringGazSensorEventListener implements GazSensorEventListener<GazSensorEvent> {
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void onGazSensorEvent(String sensorName, GazSensorEvent gazSensorEvent) {
		applicationEventPublisher.publishEvent(new GazSensorApplicationEvent<>(sensorName, gazSensorEvent));
	}
}
