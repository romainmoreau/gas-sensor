package fr.romainmoreau.gassensor.web.jserialcomm;

import java.util.List;

public class JSerialCommProperties {
	private List<JSerialCommGasSensor> gasSensors;

	public List<JSerialCommGasSensor> getGasSensors() {
		return gasSensors;
	}

	public void setGasSensors(List<JSerialCommGasSensor> gasSensors) {
		this.gasSensors = gasSensors;
	}
}
