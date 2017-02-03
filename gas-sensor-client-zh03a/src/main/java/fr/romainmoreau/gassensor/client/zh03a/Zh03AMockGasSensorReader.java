package fr.romainmoreau.gassensor.client.zh03a;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.MockGasSensorReader;

public class Zh03AMockGasSensorReader extends MockGasSensorReader<GasSensorEvent> {
	public Zh03AMockGasSensorReader(GasSensorClient<GasSensorEvent> gasSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<GasSensorEvent> gasSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		super(gasSensorClient, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gasSensorEventToEvent(GasSensorEvent gasSensorEvent) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(Zh03A.EVENT_LENGTH);
		byteBuffer.put(Zh03A.HEADER);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Zh03A.PM1_0_DESCRIPTION)).map(GasSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Zh03A.PM2_5_DESCRIPTION)).map(GasSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Zh03A.PM10_DESCRIPTION)).map(GasSensing::getValue).findFirst()
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
