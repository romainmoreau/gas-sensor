package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class MockGasSensorReader<E extends GasSensorEvent> implements GasSensorReader {
	private final GasSensorClient<E> gasSensorClient;

	private final Supplier<Long> sleepMillisSupplier;

	private final Supplier<E> gasSensorEventSupplier;

	private final Consumer<Exception> exceptionConsumer;

	private final Thread thread;

	private volatile boolean stop;

	public MockGasSensorReader(GasSensorClient<E> gasSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<E> gasSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		this.gasSensorClient = gasSensorClient;
		this.sleepMillisSupplier = sleepMillisSupplier;
		this.gasSensorEventSupplier = gasSensorEventSupplier;
		this.exceptionConsumer = exceptionConsumer;
		thread = new Thread(this::run);
		thread.start();
	}

	protected abstract byte[] gasSensorEventToEvent(E gasSensorEvent);

	private void run() {
		while (!stop) {
			try {
				gasSensorClient.onReadBytes(gasSensorEventToEvent(gasSensorEventSupplier.get()));
				try {
					Thread.sleep(sleepMillisSupplier.get());
				} catch (InterruptedException e) {
				}
			} catch (Exception e) {
				exceptionConsumer.accept(e);
			}
		}
	}

	@Override
	public void close() throws IOException {
		stop = true;
		thread.interrupt();
	}
}
