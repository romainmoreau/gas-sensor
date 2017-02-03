package fr.romainmoreau.gassensor.client.zph01;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;

public class MockZph01GasSensorClient extends Zph01GasSensorClient {
	public MockZph01GasSensorClient(Supplier<Long> sleepMillisSupplier, Supplier<GasSensorEvent> gasSensorEventSupplier,
			Consumer<Exception> exceptionConsumer, GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(c -> new Zph01MockGasSensorReader(c, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer),
				gasSensorEventListener, gasSensorExceptionHandler);
	}
}
