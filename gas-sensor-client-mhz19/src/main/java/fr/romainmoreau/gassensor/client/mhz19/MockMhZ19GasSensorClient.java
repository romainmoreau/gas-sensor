package fr.romainmoreau.gassensor.client.mhz19;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;

public class MockMhZ19GasSensorClient extends MhZ19GasSensorClient {
	public MockMhZ19GasSensorClient(String description, Supplier<Long> sleepMillisSupplier,
			Supplier<GasSensorEvent> gasSensorEventSupplier, Consumer<Exception> exceptionConsumer,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description,
				c -> new MhZ19MockGasSensorReader(c, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer),
				gasSensorEventListener, gasSensorExceptionHandler);
	}
}
