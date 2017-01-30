package fr.romainmoreau.gazsensor.client.ze07;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.ChecksumUtils;
import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorClient;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.MockGazSensorReader;

public class Ze07MockGazSensorReader extends MockGazSensorReader<GazSensorEvent> {
	public Ze07MockGazSensorReader(GazSensorClient<GazSensorEvent> gazSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<GazSensorEvent> gazSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		super(gazSensorClient, sleepMillisSupplier, gazSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gazSensorEventToEvent(GazSensorEvent gazSensorEvent) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(Ze07.EVENT_LENGTH);
		byteBuffer.put(Ze07.HEADER);
		byteBuffer.putShort((short) gazSensorEvent.getGazSensingList().stream()
				.filter(g -> g.getDescription().equals(Ze07.CO_DESCRIPTION)).map(GazSensing::getValue).findFirst().get()
				.divide(Ze07.CO_MULTIPLICAND).intValueExact());
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put(ChecksumUtils.notSum(byteBuffer.array()));
		return byteBuffer.array();
	}
}
