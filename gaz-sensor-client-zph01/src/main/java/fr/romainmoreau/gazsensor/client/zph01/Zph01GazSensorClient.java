package fr.romainmoreau.gazsensor.client.zph01;

import java.io.IOException;
import java.math.BigDecimal;

import fr.romainmoreau.gazsensor.client.common.AbstractGazSensorClient;
import fr.romainmoreau.gazsensor.client.common.ChecksumUtils;
import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;
import fr.romainmoreau.gazsensor.client.common.GazSensorReaderFactory;
import fr.romainmoreau.gazsensor.client.common.GenericGazSensorEvent;

public class Zph01GazSensorClient extends AbstractGazSensorClient<GazSensorEvent> {
	public Zph01GazSensorClient(GazSensorReaderFactory<GazSensorEvent> gazSensorReaderFactory,
			GazSensorEventListener<GazSensorEvent> gazSensorEventListener,
			GazSensorExceptionHandler gazSensorExceptionHandler) throws IOException {
		super(Zph01.SENSOR_NAME, gazSensorReaderFactory, gazSensorEventListener, gazSensorExceptionHandler,
				Zph01.EVENT_LENGTH, Zph01.CHECKSUM_LENGTH, Zph01.HEADER);
	}

	@Override
	protected GazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new GenericGazSensorEvent(
				new GazSensing(Zph01.PM2_5_DESCRIPTION,
						new BigDecimal(event[3] * Zph01.PM2_5_MULTIPLICAND + event[4]).divide(Zph01.PM2_5_DIVISOR),
						Zph01.PM2_5_UNIT),
				new GazSensing(Zph01.VOC_DESCRIPTION, new BigDecimal(event[5]), Zph01.VOC_UNIT));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.notSum(event) };
	}
}
