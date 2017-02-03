package fr.romainmoreau.gassensor.client.zph01;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.MockGasSensorReader;

public class Zph01MockGasSensorReader extends MockGasSensorReader<GasSensorEvent> {
	public Zph01MockGasSensorReader(GasSensorClient<GasSensorEvent> gasSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<GasSensorEvent> gasSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		super(gasSensorClient, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gasSensorEventToEvent(GasSensorEvent gasSensorEvent) {
		int pm25 = gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Zph01.PM2_5_DESCRIPTION)).map(GasSensing::getValue).findFirst()
				.get().multiply(Zph01.PM2_5_DIVISOR).intValueExact();
		ByteBuffer byteBuffer = ByteBuffer.allocate(Zph01.EVENT_LENGTH);
		byteBuffer.put(Zph01.HEADER);
		byteBuffer.put((byte) (pm25 / Zph01.PM2_5_MULTIPLICAND));
		byteBuffer.put((byte) (pm25 % Zph01.PM2_5_MULTIPLICAND));
		byteBuffer.put((byte) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Zph01.VOC_DESCRIPTION)).map(GasSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put(ChecksumUtils.notSum(byteBuffer.array()));
		return byteBuffer.array();
	}
}
