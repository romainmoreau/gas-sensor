package fr.romainmoreau.gassensor.client.mhz19;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.JSerialCommPollerGasSensorReader;

public class JSerialCommMhZ19GasSensorClient extends MhZ19GasSensorClient {
	public JSerialCommMhZ19GasSensorClient(String description, String portName,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description,
				c -> new JSerialCommPollerGasSensorReader(c, portName, 1000, (byte) -1, (byte) 1, (byte) 134, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 121),
				gasSensorEventListener, gasSensorExceptionHandler);
	}
}
