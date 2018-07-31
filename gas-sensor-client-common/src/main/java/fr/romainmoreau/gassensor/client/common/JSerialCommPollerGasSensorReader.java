package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

public class JSerialCommPollerGasSensorReader extends JSerialCommGasSensorReader {
	private final long sleep;

	private final byte[] command;

	private final Thread thread;

	private volatile boolean stop;

	public JSerialCommPollerGasSensorReader(GasSensorClient<?> gasSensorClient, String portName, long sleep,
			byte... command) throws IOException {
		super(gasSensorClient, portName);
		this.sleep = sleep;
		this.command = command;
		thread = new Thread(this::run);
		thread.start();
	}

	private void run() {
		while (!stop) {
			try {
				int bytesWritten = serialPort.writeBytes(command, command.length);
				if (bytesWritten != command.length) {
					throw new IOException("Error writing bytes");
				}
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
				}
			} catch (Exception e) {
				gasSensorClient.getGasSensorExceptionHandler().onReadBytesError(gasSensorClient.getSensorName(), e);
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
