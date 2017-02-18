package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

public class JsscPollerGasSensorReader extends JsscGasSensorReader {
	private final long sleep;

	private final byte[] command;

	private final Thread thread;

	private volatile boolean stop;

	public JsscPollerGasSensorReader(GasSensorClient<?> gasSensorClient, String portName, long sleep, byte... command)
			throws IOException {
		super(gasSensorClient, portName);
		this.sleep = sleep;
		this.command = command;
		thread = new Thread(this::run);
		thread.start();
	}

	private void run() {
		while (!stop) {
			try {
				serialPort.writeBytes(command);
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
				}
			} catch (Exception e) {
				gasSensorClient.getGasSensorExceptionHandler().onReadBytesError(e);
			}
		}
	}

	@Override
	public void close() throws IOException {
		super.close();
		stop = true;
		thread.interrupt();
	}
}
