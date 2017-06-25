package fr.romainmoreau.gassensor.client.zh03a;

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

public class Zh03AGasSensorClient extends AbstractGasSensorClient<GasSensorEvent> {
	public Zh03AGasSensorClient(String description, GasSensorReaderFactory<GasSensorEvent> gasSensorReaderFactory,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description != null ? Zh03A.SENSOR_NAME + description : Zh03A.SENSOR_NAME, gasSensorReaderFactory,
				gasSensorEventListener, gasSensorExceptionHandler,
				new FixedLengthGasSensorEventAnalyser(Zh03A.EVENT_LENGTH),
				new ChecksumGasSensorEventValidator(Zh03A.CHECKSUM_LENGTH, ChecksumUtils::twoBytesSum), Zh03A.HEADER);
	}

	@Override
	protected GasSensorEvent eventToGasSensorEvent(byte[] event) {
		return new GenericGasSensorEvent(
				new GasSensing(Zh03A.PM1_0_DESCRIPTION, ByteUtils.highByteLowByteToBigDecimal(event[10], event[11]),
						Zh03A.PM_UNIT),
				new GasSensing(Zh03A.PM2_5_DESCRIPTION, ByteUtils.highByteLowByteToBigDecimal(event[12], event[13]),
						Zh03A.PM_UNIT),
				new GasSensing(Zh03A.PM10_DESCRIPTION, ByteUtils.highByteLowByteToBigDecimal(event[14], event[15]),
						Zh03A.PM_UNIT));
	}
}
