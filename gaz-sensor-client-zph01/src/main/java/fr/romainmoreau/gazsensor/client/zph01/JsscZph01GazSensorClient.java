package fr.romainmoreau.gazsensor.client.zph01;

import java.io.IOException;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;
import fr.romainmoreau.gazsensor.client.common.JsscGazSensorReader;

public class JsscZph01GazSensorClient extends Zph01GazSensorClient {
	public JsscZph01GazSensorClient(String portName, GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(c -> new JsscGazSensorReader(c, portName), gazSensorEventListener, gazSensorExceptionHandler);
	}
}
