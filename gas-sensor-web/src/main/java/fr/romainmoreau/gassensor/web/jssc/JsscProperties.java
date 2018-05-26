package fr.romainmoreau.gassensor.web.jssc;

import java.util.List;

public class JsscProperties {
	private List<JsscGasSensor> gasSensors;

	public List<JsscGasSensor> getGasSensors() {
		return gasSensors;
	}

	public void setGasSensors(List<JsscGasSensor> gasSensors) {
		this.gasSensors = gasSensors;
	}
}
