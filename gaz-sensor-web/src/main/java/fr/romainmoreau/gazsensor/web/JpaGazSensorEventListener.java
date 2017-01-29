package fr.romainmoreau.gazsensor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;

@Component
public class JpaGazSensorEventListener {
	@Autowired
	private GazSensingUpdateRepository gazSensingUpdateRepository;

	@EventListener(GazSensorApplicationEvent.class)
	public void onGazSensorApplicationEvent(GazSensorApplicationEvent<GazSensorEvent> gazSensorApplicationEvent) {
		for (GazSensing gazSensing : gazSensorApplicationEvent.getGazSensorEvent().getGazSensingList()) {
			GazSensingUpdate lastGazSensingUpdate = gazSensingUpdateRepository
					.findFirstBySensorNameAndDescriptionAndUnitOrderByIdDesc(gazSensorApplicationEvent.getSensorName(),
							gazSensing.getDescription(), gazSensing.getUnit());
			if (lastGazSensingUpdate == null || lastGazSensingUpdate.getValue().compareTo(gazSensing.getValue()) != 0) {
				GazSensingUpdate gazSensingUpdate = new GazSensingUpdate();
				gazSensingUpdate.setDescription(gazSensing.getDescription());
				gazSensingUpdate.setLocalDateTime(gazSensorApplicationEvent.getGazSensorEvent().getLocalDateTime());
				gazSensingUpdate.setSensorName(gazSensorApplicationEvent.getSensorName());
				gazSensingUpdate.setUnit(gazSensing.getUnit());
				gazSensingUpdate.setValue(gazSensing.getValue());
				gazSensingUpdateRepository.save(gazSensingUpdate);
			}
		}
	}
}
