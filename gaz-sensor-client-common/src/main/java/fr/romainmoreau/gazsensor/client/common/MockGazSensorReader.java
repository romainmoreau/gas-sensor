package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class MockGazSensorReader<E extends GazSensorEvent> implements GazSensorReader {
	private final GazSensorClient<E> gazSensorClient;

	private final Supplier<Long> sleepMillisSupplier;

	private final Supplier<E> gazSensorEventSupplier;

	private final Consumer<Exception> exceptionConsumer;

	private final Thread thread;

	private volatile boolean stop;

	public MockGazSensorReader(GazSensorClient<E> gazSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<E> gazSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		this.gazSensorClient = gazSensorClient;
		this.sleepMillisSupplier = sleepMillisSupplier;
		this.gazSensorEventSupplier = gazSensorEventSupplier;
		this.exceptionConsumer = exceptionConsumer;
		thread = new Thread(this::run);
		thread.start();
	}

	protected abstract byte[] gazSensorEventToEvent(E gazSensorEvent);

	private void run() {
		while (!stop) {
			try {
				gazSensorClient.onReadBytes(gazSensorEventToEvent(gazSensorEventSupplier.get()));
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
