package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

public class JsscGasSensorReader implements GasSensorReader {
	protected final GasSensorClient<?> gasSensorClient;

	protected final SerialPort serialPort;

	public JsscGasSensorReader(GasSensorClient<?> gasSensorClient, String portName) throws IOException {
		this.gasSensorClient = gasSensorClient;
		try {
			serialPort = new SerialPort(portName);
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
			serialPort.addEventListener(this::serialEvent, SerialPort.MASK_RXCHAR | SerialPort.MASK_RXFLAG);
		} catch (SerialPortException e) {
			throw new IOException(e);
		}
	}

	@Override
	public synchronized void close() throws IOException {
		try {
			serialPort.closePort();
		} catch (SerialPortException e) {
			throw new IOException(e);
		}
	}

	private void serialEvent(SerialPortEvent serialPortEvent) {
		try {
			byte[] readBytes = serialPort.readBytes();
			if (readBytes != null) {
				gasSensorClient.onReadBytes(readBytes);
			}
		} catch (Exception e) {
			gasSensorClient.getGasSensorExceptionHandler().onReadBytesError(gasSensorClient.getSensorName(), e);
		}
	}
}
