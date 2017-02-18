package fr.romainmoreau.gassensor.client.mhz19;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.MockGasSensorReader;

public class MhZ19MockGasSensorReader extends MockGasSensorReader<GasSensorEvent> {
	public MhZ19MockGasSensorReader(GasSensorClient<GasSensorEvent> gasSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<GasSensorEvent> gasSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		super(gasSensorClient, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gasSensorEventToEvent(GasSensorEvent gasSensorEvent) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(MhZ19.EVENT_LENGTH);
		byteBuffer.put(MhZ19.HEADER);
		byteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(MhZ19.CO2_DESCRIPTION)).map(GasSensing::getValue).findFirst()
				.get().intValueExact());
		byteBuffer.put((byte) 71);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put(ChecksumUtils.notSum(byteBuffer.array()));
		return byteBuffer.array();
	}
}
