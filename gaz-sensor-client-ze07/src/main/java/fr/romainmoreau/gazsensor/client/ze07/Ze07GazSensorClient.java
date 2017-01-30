package fr.romainmoreau.gazsensor.client.ze07;

import java.io.IOException;

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
	public Ze07GazSensorClient(GazSensorReaderFactory<GazSensorEvent> gazSensorReaderFactory,
			GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(Ze07.SENSOR_NAME, gazSensorReaderFactory, gazSensorEventListener, gazSensorExceptionHandler,
				Ze07.EVENT_LENGTH, Ze07.CHECKSUM_LENGTH, Ze07.HEADER);
	}

	@Override
	protected GazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new GenericGazSensorEvent(new GazSensing(Ze07.CO_DESCRIPTION,
				ByteUtils.highByteLowByteToBigDecimal(event[4], event[5]).multiply(Ze07.CO_MULTIPLICAND),
				Ze07.CO_UNIT));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.notSum(event) };
	}
}
