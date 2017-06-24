package fr.romainmoreau.gassensor.client.ze07;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;

public class MockZe07GasSensorClient extends Ze07GasSensorClient {
	public MockZe07GasSensorClient(String description, Supplier<Long> sleepMillisSupplier,
			Supplier<GasSensorEvent> gasSensorEventSupplier, Consumer<Exception> exceptionConsumer,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description,
				c -> new Ze07MockGasSensorReader(c, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer),
				gasSensorEventListener, gasSensorExceptionHandler);
	}
}
