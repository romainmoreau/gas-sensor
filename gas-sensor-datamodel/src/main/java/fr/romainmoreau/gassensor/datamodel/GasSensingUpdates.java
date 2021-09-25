package fr.romainmoreau.gassensor.datamodel;

import java.util.List;

public class GasSensingUpdates {
	private List<GasSensingUpdate> periodUpdates;

	private GasSensingUpdate firstOutOfPeriodUpdate;

	public GasSensingUpdates() {
	}

	public GasSensingUpdates(List<GasSensingUpdate> periodUpdates, GasSensingUpdate firstOutOfPeriodUpdate) {
		this.periodUpdates = periodUpdates;
		this.firstOutOfPeriodUpdate = firstOutOfPeriodUpdate;
	}

	public List<GasSensingUpdate> getPeriodUpdates() {
		return periodUpdates;
	}

	public void setPeriodUpdates(List<GasSensingUpdate> periodUpdates) {
		this.periodUpdates = periodUpdates;
	}

	public GasSensingUpdate getFirstOutOfPeriodUpdate() {
		return firstOutOfPeriodUpdate;
	}

	public void setFirstOutOfPeriodUpdate(GasSensingUpdate firstOutOfPeriodUpdate) {
		this.firstOutOfPeriodUpdate = firstOutOfPeriodUpdate;
	}
}
