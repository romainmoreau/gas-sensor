package fr.romainmoreau.gassensor.client.pmsa003;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.MockGasSensorReader;

public class Pmsa003MockGasSensorReader extends MockGasSensorReader<GasSensorEvent> {
	public Pmsa003MockGasSensorReader(GasSensorClient<GasSensorEvent> gasSensorClient,
			Supplier<Long> sleepMillisSupplier, Supplier<GasSensorEvent> gasSensorEventSupplier,
			Consumer<Exception> exceptionConsumer) {
		super(gasSensorClient, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gasSensorEventToEvent(GasSensorEvent gasSensorEvent) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(Pmsa003.EVENT_LENGTH);
		byteBuffer.put(Pmsa003.HEADER);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Pmsa003.PM1_0_DESCRIPTION)).map(GasSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Pmsa003.PM2_5_DESCRIPTION)).map(GasSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Pmsa003.PM10_DESCRIPTION)).map(GasSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put(ChecksumUtils.twoBytesUnsignedSum(byteBuffer.array()));
		return byteBuffer.array();
	}
}
