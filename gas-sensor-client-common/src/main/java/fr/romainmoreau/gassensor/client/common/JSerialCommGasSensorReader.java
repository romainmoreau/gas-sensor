package fr.romainmoreau.gassensor.client.common;

import static com.fazecast.jSerialComm.SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
import static com.fazecast.jSerialComm.SerialPort.NO_PARITY;
import static com.fazecast.jSerialComm.SerialPort.ONE_STOP_BIT;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class JSerialCommGasSensorReader implements GasSensorReader, SerialPortDataListener {
	protected final GasSensorClient<?> gasSensorClient;

	protected final SerialPort serialPort;

	public JSerialCommGasSensorReader(GasSensorClient<?> gasSensorClient, String portName) throws IOException {
		this.gasSensorClient = gasSensorClient;
		serialPort = SerialPort.getCommPort(portName);
		serialPort.setBaudRate(9600);
		serialPort.setNumDataBits(8);
		serialPort.setNumStopBits(ONE_STOP_BIT);
		serialPort.setParity(NO_PARITY);
		serialPort.addDataListener(this);
		if (!serialPort.openPort()) {
			throw new IOException("Port not opened");
		}
	}

	@Override
	public int getListeningEvents() {
		return LISTENING_EVENT_DATA_AVAILABLE;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		try {
			byte[] readBytes = readBytes();
			if (readBytes != null) {
				gasSensorClient.onReadBytes(readBytes);
			}
		} catch (Exception e) {
			gasSensorClient.getGasSensorExceptionHandler().onReadBytesError(gasSensorClient.getSensorName(), e);
		}
	}

	@Override
	public synchronized void close() throws IOException {
		if (!serialPort.closePort()) {
			throw new IOException("Port not closed");
		}
	}

	private byte[] readBytes() {
		int bytesAvailable = serialPort.bytesAvailable();
		if (bytesAvailable == 0) {
			return null;
		} else if (bytesAvailable > 0) {
			byte[] bytes = new byte[bytesAvailable];
			if (serialPort.readBytes(bytes, bytes.length) == bytes.length) {
				return bytes;
			} else {
				throw new RuntimeException("Error reading bytes");
			}
		} else {
			throw new RuntimeException("Port not opened");
		}
	}
}
