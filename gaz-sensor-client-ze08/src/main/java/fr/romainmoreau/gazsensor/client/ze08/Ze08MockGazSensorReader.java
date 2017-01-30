package fr.romainmoreau.gazsensor.client.ze08;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.ChecksumUtils;
import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorClient;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.MockGazSensorReader;

public class Ze08MockGazSensorReader extends MockGazSensorReader<GazSensorEvent> {
	public Ze08MockGazSensorReader(GazSensorClient<GazSensorEvent> gazSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<GazSensorEvent> gazSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		super(gazSensorClient, sleepMillisSupplier, gazSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gazSensorEventToEvent(GazSensorEvent gazSensorEvent) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(Ze08.EVENT_LENGTH);
		byteBuffer.put(Ze08.HEADER);
		byteBuffer.putShort((short) gazSensorEvent.getGazSensingList().stream()
				.filter(g -> g.getDescription().equals(Ze08.CH2O_DESCRIPTION)).map(GazSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put(ChecksumUtils.notSum(byteBuffer.array()));
		return byteBuffer.array();
	}
}
