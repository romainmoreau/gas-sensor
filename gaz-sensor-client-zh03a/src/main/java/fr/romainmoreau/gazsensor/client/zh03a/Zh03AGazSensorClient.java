package fr.romainmoreau.gazsensor.client.zh03a;

import java.io.IOException;

import fr.romainmoreau.gazsensor.client.common.AbstractGazSensorClient;
import fr.romainmoreau.gazsensor.client.common.ByteUtils;
import fr.romainmoreau.gazsensor.client.common.ChecksumUtils;
import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorReaderFactory;
import fr.romainmoreau.gazsensor.client.common.GenericGazSensorEvent;

public class Zh03AGazSensorClient extends AbstractGazSensorClient<GazSensorEvent> {
	public Zh03AGazSensorClient(GazSensorReaderFactory gazSensorReaderFactory,
			GazSensorEventListener<GazSensorEvent> gazSensorEventListener) throws IOException {
		super("ZH03A", gazSensorReaderFactory, gazSensorEventListener, 24, 2, (byte) 66, (byte) 77, (byte) 0,
				(byte) 20);
	}

	@Override
	protected GazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new GenericGazSensorEvent(
				new GazSensing("PM1.0", ByteUtils.highByteLowByteToInt(event[10], event[11]), "ug/m3"),
				new GazSensing("PM2.5", ByteUtils.highByteLowByteToInt(event[12], event[13]), "ug/m3"),
				new GazSensing("PM10", ByteUtils.highByteLowByteToInt(event[14], event[15]), "ug/m3"));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return ChecksumUtils.twoBytesSum(event);
	}
}
