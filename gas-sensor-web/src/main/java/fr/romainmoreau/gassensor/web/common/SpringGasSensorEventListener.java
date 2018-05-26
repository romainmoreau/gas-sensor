package fr.romainmoreau.gassensor.web.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;

@Component
public class SpringGasSensorEventListener implements GasSensorEventListener<GasSensorEvent> {
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void onGasSensorEvent(String sensorName, GasSensorEvent gasSensorEvent) {
		applicationEventPublisher.publishEvent(new GasSensorApplicationEvent<>(sensorName, gasSensorEvent));
	}
}
