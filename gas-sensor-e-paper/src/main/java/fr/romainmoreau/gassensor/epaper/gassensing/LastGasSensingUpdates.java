package fr.romainmoreau.gassensor.epaper.gassensing;

import java.time.LocalDateTime;
import java.util.List;

import fr.romainmoreau.gassensor.datamodel.GasSensingUpdate;

public class LastGasSensingUpdates {
	private LocalDateTime localDateTime;

	private List<GasSensingUpdate> lastGasSensingUpdateList;

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public List<GasSensingUpdate> getLastGasSensingUpdateList() {
		return lastGasSensingUpdateList;
	}

	public void setLastGasSensingUpdateList(List<GasSensingUpdate> lastGasSensingUpdateList) {
		this.lastGasSensingUpdateList = lastGasSensingUpdateList;
	}
}
