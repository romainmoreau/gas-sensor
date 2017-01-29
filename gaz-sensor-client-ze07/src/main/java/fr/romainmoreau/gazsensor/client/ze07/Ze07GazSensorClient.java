package fr.romainmoreau.gazsensor.client.ze07;

import java.io.IOException;
import java.math.BigDecimal;

import fr.romainmoreau.gazsensor.client.common.AbstractGazSensorClient;
import fr.romainmoreau.gazsensor.client.common.ByteUtils;
import fr.romainmoreau.gazsensor.client.common.ChecksumUtils;
import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;
import fr.romainmoreau.gazsensor.client.common.GazSensorReaderFactory;
import fr.romainmoreau.gazsensor.client.common.GenericGazSensorEvent;

public class Ze07GazSensorClient extends AbstractGazSensorClient<GazSensorEvent> {
	public Ze07GazSensorClient(GazSensorReaderFactory gazSensorReaderFactory,
			GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super("ZE07", gazSensorReaderFactory, gazSensorEventListener, gazSensorExceptionHandler, 9, 1, (byte) -1,
				(byte) 4, (byte) 3, (byte) 1);
	}

	@Override
	protected GazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new GenericGazSensorEvent(new GazSensing("CO",
				ByteUtils.highByteLowByteToInt(event[4], event[5]).multiply(new BigDecimal("0.1")), "ppm"));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.notSum(event) };
	}
}
