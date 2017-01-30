package fr.romainmoreau.gazsensor.client.zph01;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;

public class MockZph01GazSensorClient extends Zph01GazSensorClient {
	public MockZph01GazSensorClient(Supplier<Long> sleepMillisSupplier, Supplier<GazSensorEvent> gazSensorEventSupplier,
			Consumer<Exception> exceptionConsumer, GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(c -> new Zph01MockGazSensorReader(c, sleepMillisSupplier, gazSensorEventSupplier, exceptionConsumer),
				gazSensorEventListener, gazSensorExceptionHandler);
	}
}
