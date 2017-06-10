package fr.romainmoreau.gassensor.client.mhz19;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.AbstractGasSensorClient;
import fr.romainmoreau.gassensor.client.common.ByteUtils;
import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.FixedLengthGasSensorEventAnalyser;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.GasSensorReaderFactory;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class MhZ19GasSensorClient extends AbstractGasSensorClient<GasSensorEvent> {
	public MhZ19GasSensorClient(GasSensorReaderFactory<GasSensorEvent> gasSensorReaderFactory,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(MhZ19.SENSOR_NAME, gasSensorReaderFactory, gasSensorEventListener, gasSensorExceptionHandler,
				new FixedLengthGasSensorEventAnalyser(MhZ19.EVENT_LENGTH), MhZ19.CHECKSUM_LENGTH, MhZ19.HEADER);
	}

	@Override
	protected GasSensorEvent eventToGasSensorEvent(byte[] event) {
		return new GenericGasSensorEvent(new GasSensing(MhZ19.CO2_DESCRIPTION,
				ByteUtils.highByteLowByteToBigDecimal(event[2], event[3]), MhZ19.CO2_UNIT));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.notSum(event) };
	}
}
