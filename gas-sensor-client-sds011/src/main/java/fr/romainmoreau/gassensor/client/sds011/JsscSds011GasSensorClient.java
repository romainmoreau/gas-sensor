package fr.romainmoreau.gassensor.client.sds011;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.JsscGasSensorReader;

public class JsscSds011GasSensorClient extends Sds011GasSensorClient {
	public JsscSds011GasSensorClient(String description, String portName,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description, c -> new JsscGasSensorReader(c, portName), gasSensorEventListener,
				gasSensorExceptionHandler);
	}
}
