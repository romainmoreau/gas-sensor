package fr.romainmoreau.gazsensor.client.ze08;

import java.io.IOException;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;
import fr.romainmoreau.gazsensor.client.common.JsscGazSensorReader;

public class JsscZe08GazSensorClient extends Ze08GazSensorClient {
	public JsscZe08GazSensorClient(String portName, GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(c -> new JsscGazSensorReader(c, portName), gazSensorEventListener, gazSensorExceptionHandler);
	}
}
