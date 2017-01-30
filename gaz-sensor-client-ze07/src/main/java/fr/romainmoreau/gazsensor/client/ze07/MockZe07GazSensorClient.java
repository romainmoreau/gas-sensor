package fr.romainmoreau.gazsensor.client.ze07;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;

public class MockZe07GazSensorClient extends Ze07GazSensorClient {
	public MockZe07GazSensorClient(Supplier<Long> sleepMillisSupplier, Supplier<GazSensorEvent> gazSensorEventSupplier,
			Consumer<Exception> exceptionConsumer, GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(c -> new Ze07MockGazSensorReader(c, sleepMillisSupplier, gazSensorEventSupplier, exceptionConsumer),
				gazSensorEventListener, gazSensorExceptionHandler);
	}
}
