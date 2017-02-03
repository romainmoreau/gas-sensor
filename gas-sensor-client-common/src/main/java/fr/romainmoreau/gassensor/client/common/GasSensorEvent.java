package fr.romainmoreau.gassensor.client.common;

import java.time.LocalDateTime;
import java.util.List;

public interface GasSensorEvent {
	LocalDateTime getLocalDateTime();

	List<GasSensing> getGasSensingList();
}
