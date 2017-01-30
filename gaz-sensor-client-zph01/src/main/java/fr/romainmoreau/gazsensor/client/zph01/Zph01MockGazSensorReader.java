package fr.romainmoreau.gazsensor.client.zph01;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gazsensor.client.common.ChecksumUtils;
import fr.romainmoreau.gazsensor.client.common.GazSensing;
import fr.romainmoreau.gazsensor.client.common.GazSensorClient;
import fr.romainmoreau.gazsensor.client.common.GazSensorEvent;
import fr.romainmoreau.gazsensor.client.common.MockGazSensorReader;

public class Zph01MockGazSensorReader extends MockGazSensorReader<GazSensorEvent> {
	public Zph01MockGazSensorReader(GazSensorClient<GazSensorEvent> gazSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<GazSensorEvent> gazSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		super(gazSensorClient, sleepMillisSupplier, gazSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gazSensorEventToEvent(GazSensorEvent gazSensorEvent) {
		int pm25 = gazSensorEvent.getGazSensingList().stream()
				.filter(g -> g.getDescription().equals(Zph01.PM2_5_DESCRIPTION)).map(GazSensing::getValue).findFirst()
				.get().multiply(Zph01.PM2_5_DIVISOR).intValueExact();
		ByteBuffer byteBuffer = ByteBuffer.allocate(Zph01.EVENT_LENGTH);
		byteBuffer.put(Zph01.HEADER);
		byteBuffer.put((byte) (pm25 / Zph01.PM2_5_MULTIPLICAND));
		byteBuffer.put((byte) (pm25 % Zph01.PM2_5_MULTIPLICAND));
		byteBuffer.put((byte) gazSensorEvent.getGazSensingList().stream()
				.filter(g -> g.getDescription().equals(Zph01.VOC_DESCRIPTION)).map(GazSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put(ChecksumUtils.notSum(byteBuffer.array()));
		return byteBuffer.array();
	}
}
