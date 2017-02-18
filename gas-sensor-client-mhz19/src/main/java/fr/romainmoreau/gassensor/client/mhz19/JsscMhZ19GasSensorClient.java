package fr.romainmoreau.gassensor.client.mhz19;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.JsscPollerGasSensorReader;

public class JsscMhZ19GasSensorClient extends MhZ19GasSensorClient {
	public JsscMhZ19GasSensorClient(String portName, GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(c -> new JsscPollerGasSensorReader(c, portName, 1000, (byte) -1, (byte) 1, (byte) 134, (byte) 0, (byte) 0,
				(byte) 0, (byte) 0, (byte) 0, (byte) 121), gasSensorEventListener, gasSensorExceptionHandler);
	}
}
