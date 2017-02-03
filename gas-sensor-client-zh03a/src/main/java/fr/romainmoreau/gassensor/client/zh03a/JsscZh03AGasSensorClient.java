package fr.romainmoreau.gassensor.client.zh03a;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.JsscGasSensorReader;

public class JsscZh03AGasSensorClient extends Zh03AGasSensorClient {
	public JsscZh03AGasSensorClient(String portName, GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(c -> new JsscGasSensorReader(c, portName), gasSensorEventListener, gasSensorExceptionHandler);
	}
}
