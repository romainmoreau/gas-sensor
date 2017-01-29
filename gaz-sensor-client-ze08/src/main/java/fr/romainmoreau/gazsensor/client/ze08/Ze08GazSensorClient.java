package fr.romainmoreau.gazsensor.client.ze08;

import java.io.IOException;

import fr.romainmoreau.gazsensor.client.common.AbstractGazSensorClient;
import fr.romainmoreau.gazsensor.client.common.ByteUtils;
import fr.romainmoreau.gazsensor.client.common.ChecksumUtils;
import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.GazSensorEventListener;
import fr.romainmoreau.gazsensor.client.common.GazSensorReaderFactory;
import fr.romainmoreau.gazsensor.client.common.GenericGazSensorEvent;

public class Ze08GazSensorClient extends AbstractGazSensorClient<GazSensorEvent> {
	public Ze08GazSensorClient(GazSensorReaderFactory gazSensorReaderFactory,
			GazSensorEventListener<GazSensorEvent> gazSensorEventListener) throws IOException {
		super("ZE08", gazSensorReaderFactory, gazSensorEventListener, 9, 1, (byte) -1, (byte) 23, (byte) 4, (byte) 0);
	}

	@Override
	protected GazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new GenericGazSensorEvent(
				new GazSensing("CH2O", ByteUtils.highByteLowByteToInt(event[4], event[5]), "ppb"));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.notSum(event) };
	}
}
