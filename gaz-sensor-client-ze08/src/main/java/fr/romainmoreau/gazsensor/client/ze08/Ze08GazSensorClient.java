package fr.romainmoreau.gazsensor.client.ze08;

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

public class Ze08GazSensorClient extends AbstractGazSensorClient<GazSensorEvent> {
	public Ze08GazSensorClient(GazSensorReaderFactory<GazSensorEvent> gazSensorReaderFactory,
			GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(Ze08.SENSOR_NAME, gazSensorReaderFactory, gazSensorEventListener, gazSensorExceptionHandler,
				Ze08.EVENT_LENGTH, Ze08.CHECKSUM_LENGTH, Ze08.HEADER);
	}

	@Override
	protected GazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new GenericGazSensorEvent(new GazSensing(Ze08.CH2O_DESCRIPTION,
				ByteUtils.highByteLowByteToBigDecimal(event[4], event[5]), Ze08.CH2O_UNIT));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.notSum(event) };
	}
}
