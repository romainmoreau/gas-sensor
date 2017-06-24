package fr.romainmoreau.gassensor.client.ze08;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;

public class MockZe08GasSensorClient extends Ze08GasSensorClient {
	public MockZe08GasSensorClient(String description, Supplier<Long> sleepMillisSupplier,
			Supplier<GasSensorEvent> gasSensorEventSupplier, Consumer<Exception> exceptionConsumer,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description,
				c -> new Ze08MockGasSensorReader(c, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer),
				gasSensorEventListener, gasSensorExceptionHandler);
	}
}
