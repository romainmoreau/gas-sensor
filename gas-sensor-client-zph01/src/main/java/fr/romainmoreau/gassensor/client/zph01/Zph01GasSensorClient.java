package fr.romainmoreau.gassensor.client.zph01;

import java.io.IOException;
import java.math.BigDecimal;

import fr.romainmoreau.gassensor.client.common.AbstractGasSensorClient;
import fr.romainmoreau.gassensor.client.common.ChecksumGasSensorEventValidator;
import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.FixedLengthGasSensorEventAnalyser;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.GasSensorReaderFactory;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class Zph01GasSensorClient extends AbstractGasSensorClient<GasSensorEvent> {
	public Zph01GasSensorClient(String description, GasSensorReaderFactory<GasSensorEvent> gasSensorReaderFactory,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(description != null ? Zph01.SENSOR_NAME + description : Zph01.SENSOR_NAME, gasSensorReaderFactory,
				gasSensorEventListener, gasSensorExceptionHandler,
				new FixedLengthGasSensorEventAnalyser(Zph01.EVENT_LENGTH), new ChecksumGasSensorEventValidator(
						Zph01.CHECKSUM_LENGTH, event -> new byte[] { ChecksumUtils.notSum(event) }),
				Zph01.HEADER);
	}

	@Override
	protected GasSensorEvent eventToGasSensorEvent(byte[] event) {
		return new GenericGasSensorEvent(
				new GasSensing(Zph01.PM2_5_DESCRIPTION,
						new BigDecimal(event[3] * Zph01.PM2_5_MULTIPLICAND + event[4]).divide(Zph01.PM2_5_DIVISOR),
						Zph01.PM2_5_UNIT),
				new GasSensing(Zph01.VOC_DESCRIPTION, new BigDecimal(event[5]), Zph01.VOC_UNIT));
	}
}
