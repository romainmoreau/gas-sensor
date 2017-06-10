package fr.romainmoreau.gassensor.client.si7021;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;

public class MockSi7021GasSensorClient extends Si7021GasSensorClient {
	public MockSi7021GasSensorClient(Supplier<Long> sleepMillisSupplier, Supplier<GasSensorEvent> gasSensorEventSupplier,
			Consumer<Exception> exceptionConsumer, GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(c -> new Si7021MockGasSensorReader(c, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer),
				gasSensorEventListener, gasSensorExceptionHandler);
	}
}
