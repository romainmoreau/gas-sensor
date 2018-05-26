package fr.romainmoreau.gassensor.web.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.web.common.GasSensorApplicationEvent;

@Component
public class JpaGasSensorEventListener {
	@Autowired
	private GasSensingUpdateRepository gasSensingUpdateRepository;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@EventListener(GasSensorApplicationEvent.class)
	public void onGasSensorApplicationEvent(GasSensorApplicationEvent<GasSensorEvent> gasSensorApplicationEvent) {
		for (GasSensing gasSensing : gasSensorApplicationEvent.getGasSensorEvent().getGasSensingList()) {
			GasSensingUpdate lastGasSensingUpdate = gasSensingUpdateRepository
					.findFirstBySensorNameAndDescriptionAndUnitOrderByIdDesc(gasSensorApplicationEvent.getSensorName(),
							gasSensing.getDescription(), gasSensing.getUnit());
			if (lastGasSensingUpdate == null || lastGasSensingUpdate.getValue().compareTo(gasSensing.getValue()) != 0) {
				GasSensingUpdate gasSensingUpdate = new GasSensingUpdate();
				gasSensingUpdate.setDescription(gasSensing.getDescription());
				gasSensingUpdate.setLocalDateTime(gasSensorApplicationEvent.getGasSensorEvent().getLocalDateTime());
				gasSensingUpdate.setSensorName(gasSensorApplicationEvent.getSensorName());
				gasSensingUpdate.setUnit(gasSensing.getUnit());
				gasSensingUpdate.setValue(gasSensing.getValue());
				gasSensingUpdateRepository.save(gasSensingUpdate);
				StringBuilder destinationStringBuilder = new StringBuilder();
				destinationStringBuilder.append("/updates/");
				destinationStringBuilder.append(gasSensingUpdate.getSensorName());
				destinationStringBuilder.append("/");
				destinationStringBuilder.append(gasSensingUpdate.getDescription());
				destinationStringBuilder.append("/");
				destinationStringBuilder.append(gasSensingUpdate.getUnit());
				simpMessagingTemplate.convertAndSend(destinationStringBuilder.toString(), gasSensingUpdate);
			}
		}
	}
}
