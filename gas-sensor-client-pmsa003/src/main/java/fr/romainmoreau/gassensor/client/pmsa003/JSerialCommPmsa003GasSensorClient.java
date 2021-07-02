package fr.romainmoreau.gassensor.client.pmsa003;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.JSerialCommGasSensorReader;

public class JSerialCommPmsa003GasSensorClient extends Pmsa003GasSensorClient {
	public JSerialCommPmsa003GasSensorClient(String description, String portName,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description, c -> new JSerialCommGasSensorReader(c, portName), gasSensorEventListener,
				gasSensorExceptionHandler);
	}
}
