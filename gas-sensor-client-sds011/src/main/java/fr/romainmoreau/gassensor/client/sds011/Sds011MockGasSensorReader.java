package fr.romainmoreau.gassensor.client.sds011;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.MockGasSensorReader;

public class Sds011MockGasSensorReader extends MockGasSensorReader<GasSensorEvent> {
	public Sds011MockGasSensorReader(GasSensorClient<GasSensorEvent> gasSensorClient,
			Supplier<Long> sleepMillisSupplier, Supplier<GasSensorEvent> gasSensorEventSupplier,
			Consumer<Exception> exceptionConsumer) {
		super(gasSensorClient, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gasSensorEventToEvent(GasSensorEvent gasSensorEvent) {
		ByteBuffer dataByteBuffer = ByteBuffer.allocate(6);
		dataByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		dataByteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Sds011.PM2_5_DESCRIPTION)).map(GasSensing::getValue)
				.map(value -> value.scaleByPowerOfTen(1)).findFirst().get().intValueExact());
		dataByteBuffer.putShort((short) gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Sds011.PM10_DESCRIPTION)).map(GasSensing::getValue)
				.map(value -> value.scaleByPowerOfTen(1)).findFirst().get().intValueExact());
		dataByteBuffer.put((byte) 0);
		dataByteBuffer.put((byte) 0);
		ByteBuffer byteBuffer = ByteBuffer.allocate(Sds011.EVENT_LENGTH);
		byteBuffer.put(Sds011.HEADER);
		byteBuffer.put(dataByteBuffer.array());
		byteBuffer.put(ChecksumUtils.sum(dataByteBuffer.array()));
		byteBuffer.put(Sds011.TAIL);
		return byteBuffer.array();
	}
}
