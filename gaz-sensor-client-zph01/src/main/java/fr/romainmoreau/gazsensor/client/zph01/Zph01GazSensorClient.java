package fr.romainmoreau.gazsensor.client.zph01;

import java.io.IOException;
import java.math.BigDecimal;

import fr.romainmoreau.gazsensor.client.common.AbstractGazSensorClient;
import fr.romainmoreau.gazsensor.client.common.ChecksumUtils;
import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorReaderFactory;
import fr.romainmoreau.gazsensor.client.common.GenericGazSensorEvent;

public class Zph01GazSensorClient extends AbstractGazSensorClient<GazSensorEvent> {
	public Zph01GazSensorClient(GazSensorReaderFactory gazSensorReaderFactory,
			GazSensorEventListener<GazSensorEvent> gazSensorEventListener) throws IOException {
		super("ZPH01", gazSensorReaderFactory, gazSensorEventListener, 9, 1, (byte) -1, (byte) 24, (byte) 0);
	}

	@Override
	protected GazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new GenericGazSensorEvent(new GazSensing("PM2.5",
				new BigDecimal(event[3] * 100 + event[4]).divide(new BigDecimal(100)), "% (Low pulse rate)"),
				new GazSensing("VOC", new BigDecimal(event[5]), "(0-3)"));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.notSum(event) };
	}
}
