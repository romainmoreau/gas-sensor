package fr.romainmoreau.gassensor.client.si7021;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.JsscGasSensorReader;

public class JsscSi7021GasSensorClient extends Si7021GasSensorClient {
	public JsscSi7021GasSensorClient(String description, String portName,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description, c -> new JsscGasSensorReader(c, portName), gasSensorEventListener,
				gasSensorExceptionHandler);
	}
}
