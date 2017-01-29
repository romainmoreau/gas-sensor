package fr.romainmoreau.gazsensor.client.ze07;

import java.io.IOException;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.JsscGazSensorReader;

public class JsscZe07GazSensorClient extends Ze07GazSensorClient {
	public JsscZe07GazSensorClient(String portName, GazSensorEventListener<GazSensorEvent> gazSensorEventListener)
			throws IOException {
		super(c -> new JsscGazSensorReader(c, portName), gazSensorEventListener);
	}
}
