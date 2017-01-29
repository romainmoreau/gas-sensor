package fr.romainmoreau.gazsensor.client.zh03a;

import java.io.IOException;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;
import fr.romainmoreau.gazsensor.client.common.JsscGazSensorReader;

public class JsscZh03AGazSensorClient extends Zh03AGazSensorClient {
	public JsscZh03AGazSensorClient(String portName, GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(c -> new JsscGazSensorReader(c, portName), gazSensorEventListener, gazSensorExceptionHandler);
	}
}
