package fr.romainmoreau.gassensor.client.ze08;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.AbstractGasSensorClient;
import fr.romainmoreau.gassensor.client.common.ByteUtils;
import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.GasSensorReaderFactory;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class Ze08GasSensorClient extends AbstractGasSensorClient<GasSensorEvent> {
	public Ze08GasSensorClient(GasSensorReaderFactory<GasSensorEvent> gasSensorReaderFactory,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(Ze08.SENSOR_NAME, gasSensorReaderFactory, gasSensorEventListener, gasSensorExceptionHandler,
				Ze08.EVENT_LENGTH, Ze08.CHECKSUM_LENGTH, Ze08.HEADER);
	}

	@Override
	protected GasSensorEvent eventToGasSensorEvent(byte[] event) {
		return new GenericGasSensorEvent(new GasSensing(Ze08.CH2O_DESCRIPTION,
				ByteUtils.highByteLowByteToBigDecimal(event[4], event[5]), Ze08.CH2O_UNIT));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.notSum(event) };
	}
}
