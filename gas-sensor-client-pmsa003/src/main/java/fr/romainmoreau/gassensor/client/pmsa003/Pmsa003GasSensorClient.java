package fr.romainmoreau.gassensor.client.pmsa003;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.AbstractGasSensorClient;
import fr.romainmoreau.gassensor.client.common.ByteUtils;
import fr.romainmoreau.gassensor.client.common.ChecksumGasSensorEventValidator;
import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.FixedLengthGasSensorEventAnalyser;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.GasSensorReaderFactory;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class Pmsa003GasSensorClient extends AbstractGasSensorClient<GasSensorEvent> {
	public Pmsa003GasSensorClient(String description, GasSensorReaderFactory<GasSensorEvent> gasSensorReaderFactory,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description != null ? Pmsa003.SENSOR_NAME + description : Pmsa003.SENSOR_NAME, gasSensorReaderFactory,
				gasSensorEventListener, gasSensorExceptionHandler,
				new FixedLengthGasSensorEventAnalyser(Pmsa003.EVENT_LENGTH),
				new ChecksumGasSensorEventValidator(Pmsa003.CHECKSUM_LENGTH, ChecksumUtils::twoBytesUnsignedSum),
				Pmsa003.HEADER);
	}

	@Override
	protected GasSensorEvent eventToGasSensorEvent(byte[] event) {
		return new GenericGasSensorEvent(
				new GasSensing(Pmsa003.PM1_0_DESCRIPTION, ByteUtils.highByteLowByteToBigDecimal(event[4], event[5]),
						Pmsa003.PM_UNIT),
				new GasSensing(Pmsa003.PM2_5_DESCRIPTION, ByteUtils.highByteLowByteToBigDecimal(event[6], event[7]),
						Pmsa003.PM_UNIT),
				new GasSensing(Pmsa003.PM10_DESCRIPTION, ByteUtils.highByteLowByteToBigDecimal(event[8], event[9]),
						Pmsa003.PM_UNIT));
	}
}
