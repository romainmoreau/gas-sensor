package fr.romainmoreau.gassensor.client.ze07;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.JsscGasSensorReader;

public class JsscZe07GasSensorClient extends Ze07GasSensorClient {
	public JsscZe07GasSensorClient(String portName, GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(c -> new JsscGasSensorReader(c, portName), gasSensorEventListener, gasSensorExceptionHandler);
	}
}
