package fr.romainmoreau.gassensor.client.ze07;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.MockGasSensorReader;

public class Ze07MockGasSensorReader extends MockGasSensorReader<GasSensorEvent> {
	public Ze07MockGasSensorReader(GasSensorClient<GasSensorEvent> gasSensorClient, Supplier<Long> sleepMillisSupplier,
			Supplier<GasSensorEvent> gasSensorEventSupplier, Consumer<Exception> exceptionConsumer) {
		super(gasSensorClient, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gasSensorEventToEvent(GasSensorEvent gasSensorEvent) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(Ze07.EVENT_LENGTH);
		byteBuffer.put(Ze07.HEADER);
		byteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Ze07.CO_DESCRIPTION)).map(GasSensing::getValue).findFirst().get()
				.divide(Ze07.CO_MULTIPLICAND).intValueExact());
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);
		byteBuffer.put(ChecksumUtils.notSum(byteBuffer.array()));
		return byteBuffer.array();
	}
}
