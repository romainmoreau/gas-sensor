package fr.romainmoreau.gassensor.client.zph01;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.JsscGasSensorReader;

public class JsscZph01GasSensorClient extends Zph01GasSensorClient {
	public JsscZph01GasSensorClient(String portName, GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(c -> new JsscGasSensorReader(c, portName), gasSensorEventListener, gasSensorExceptionHandler);
	}
}
