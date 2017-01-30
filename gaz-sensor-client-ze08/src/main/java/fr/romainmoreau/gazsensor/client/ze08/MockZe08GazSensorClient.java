package fr.romainmoreau.gazsensor.client.ze08;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;

public class MockZe08GazSensorClient extends Ze08GazSensorClient {
	public MockZe08GazSensorClient(Supplier<Long> sleepMillisSupplier, Supplier<GazSensorEvent> gazSensorEventSupplier,
			Consumer<Exception> exceptionConsumer, GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(c -> new Ze08MockGazSensorReader(c, sleepMillisSupplier, gazSensorEventSupplier, exceptionConsumer),
				gazSensorEventListener, gazSensorExceptionHandler);
	}
}
