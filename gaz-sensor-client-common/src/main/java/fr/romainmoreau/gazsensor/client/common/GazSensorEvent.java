package fr.romainmoreau.gazsensor.client.common;

import java.time.LocalDateTime;
import java.util.List;

public interface GazSensorEvent {
	LocalDateTime getLocalDateTime();

	List<GazSensing> getGazSensingList();
}
