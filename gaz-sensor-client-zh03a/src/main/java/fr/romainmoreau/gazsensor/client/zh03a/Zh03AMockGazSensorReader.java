package fr.romainmoreau.gazsensor.client.zh03a;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.ChecksumUtils;
import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorClient;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.MockGazSensorReader;

public class Zh03AMockGazSensorReader extends MockGazSensorReader<GazSensorEvent> {
	public Zh03AMockGazSensorReader(GazSensorClient<GazSensorEvent> gazSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<GazSensorEvent> gazSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		super(gazSensorClient, sleepMillisSupplier, gazSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gazSensorEventToEvent(GazSensorEvent gazSensorEvent) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(Zh03A.EVENT_LENGTH);
		byteBuffer.put(Zh03A.HEADER);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.putShort((short) gazSensorEvent.getGazSensingList().stream()
				.filter(g -> g.getDescription().equals(Zh03A.PM1_0_DESCRIPTION)).map(GazSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.putShort((short) gazSensorEvent.getGazSensingList().stream()
				.filter(g -> g.getDescription().equals(Zh03A.PM2_5_DESCRIPTION)).map(GazSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.putShort((short) gazSensorEvent.getGazSensingList().stream()
				.filter(g -> g.getDescription().equals(Zh03A.PM10_DESCRIPTION)).map(GazSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put(ChecksumUtils.twoBytesSum(byteBuffer.array()));
		return byteBuffer.array();
	}
}
