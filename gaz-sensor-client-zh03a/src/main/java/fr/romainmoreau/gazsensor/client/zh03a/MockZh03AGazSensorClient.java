package fr.romainmoreau.gazsensor.client.zh03a;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;

public class MockZh03AGazSensorClient extends Zh03AGazSensorClient {
	public MockZh03AGazSensorClient(Supplier<Long> sleepMillisSupplier, Supplier<GazSensorEvent> gazSensorEventSupplier,
			Consumer<Exception> exceptionConsumer, GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(c -> new Zh03AMockGazSensorReader(c, sleepMillisSupplier, gazSensorEventSupplier, exceptionConsumer),
				gazSensorEventListener, gazSensorExceptionHandler);
	}
}
