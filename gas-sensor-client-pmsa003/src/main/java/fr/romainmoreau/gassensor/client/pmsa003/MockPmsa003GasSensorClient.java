package fr.romainmoreau.gassensor.client.pmsa003;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;

public class MockPmsa003GasSensorClient extends Pmsa003GasSensorClient {
	public MockPmsa003GasSensorClient(String description, Supplier<Long> sleepMillisSupplier,
			Supplier<GasSensorEvent> gasSensorEventSupplier, Consumer<Exception> exceptionConsumer,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description,
				c -> new Pmsa003MockGasSensorReader(c, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer),
				gasSensorEventListener, gasSensorExceptionHandler);
	}
}
