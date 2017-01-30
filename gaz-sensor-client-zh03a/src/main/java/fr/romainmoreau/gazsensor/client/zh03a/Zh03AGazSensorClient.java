package fr.romainmoreau.gazsensor.client.zh03a;

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

public class Zh03AGazSensorClient extends AbstractGazSensorClient<GazSensorEvent> {
	public Zh03AGazSensorClient(GazSensorReaderFactory<GazSensorEvent> gazSensorReaderFactory,
			GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(Zh03A.SENSOR_NAME, gazSensorReaderFactory, gazSensorEventListener, gazSensorExceptionHandler,
				Zh03A.EVENT_LENGTH, Zh03A.CHECKSUM_LENGTH, Zh03A.HEADER);
	}

	@Override
	protected GazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new GenericGazSensorEvent(
				new GazSensing(Zh03A.PM1_0_DESCRIPTION, ByteUtils.highByteLowByteToBigDecimal(event[10], event[11]),
						Zh03A.PM_UNIT),
				new GazSensing(Zh03A.PM2_5_DESCRIPTION, ByteUtils.highByteLowByteToBigDecimal(event[12], event[13]),
						Zh03A.PM_UNIT),
				new GazSensing(Zh03A.PM10_DESCRIPTION, ByteUtils.highByteLowByteToBigDecimal(event[14], event[15]),
						Zh03A.PM_UNIT));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return ChecksumUtils.twoBytesSum(event);
	}
}
