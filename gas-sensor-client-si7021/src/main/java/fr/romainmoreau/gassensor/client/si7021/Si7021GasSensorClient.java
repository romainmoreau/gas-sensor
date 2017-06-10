package fr.romainmoreau.gassensor.client.si7021;

import java.io.IOException;
import java.math.BigDecimal;

import fr.romainmoreau.gassensor.client.common.AbstractGasSensorClient;
import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.GasSensorEventListener;
import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;
import fr.romainmoreau.gassensor.client.common.GasSensorReaderFactory;
import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class Si7021GasSensorClient extends AbstractGasSensorClient<GasSensorEvent> {
	public Si7021GasSensorClient(GasSensorReaderFactory<GasSensorEvent> gasSensorReaderFactory,
			GasSensorEventListener<GasSensorEvent> gasSensorEventListener,
			GasSensorExceptionHandler gasSensorExceptionHandler) throws IOException {
		super(Si7021.SENSOR_NAME, gasSensorReaderFactory, gasSensorEventListener, gasSensorExceptionHandler,
				new Si7021GasSensorEventAnalyser(), Si7021.CHECKSUM_LENGTH, Si7021.HEADER);
	}

	@Override
	protected GasSensorEvent eventToGasSensorEvent(byte[] event) {
		String eventString = new String(event);
		String[] eventSplits = eventString.split(Si7021.SEPARATOR_REGEX);
		return new GenericGasSensorEvent(
				new GasSensing(Si7021.TEMPERATURE_DESCRIPTION,
						new BigDecimal(eventSplits[1].substring(Si7021.TEMPERATURE_PREFIX.length()).trim()),
						Si7021.TEMPERATURE_UNIT),
				new GasSensing(Si7021.HUMIDITY_DESCRIPTION,
						new BigDecimal(eventSplits[2].substring(Si7021.HUMIDITY_PREFIX.length()).trim()),
						Si7021.HUMIDITY_UNIT));
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		throw new UnsupportedOperationException();
	}
}
