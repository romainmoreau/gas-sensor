package fr.romainmoreau.gassensor.client.si7021;

import java.util.function.Consumer;
import java.util.function.Supplier;

import fr.romainmoreau.gassensor.client.common.GasSensing;
import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.common.GasSensorEvent;
import fr.romainmoreau.gassensor.client.common.MockGasSensorReader;

public class Si7021MockGasSensorReader extends MockGasSensorReader<GasSensorEvent> {
	public Si7021MockGasSensorReader(GasSensorClient<GasSensorEvent> gasSensorClient,
			Supplier<Long> sleepMillisSupplier, Supplier<GasSensorEvent> gasSensorEventSupplier,
			Consumer<Exception> exceptionConsumer) {
		super(gasSensorClient, sleepMillisSupplier, gasSensorEventSupplier, exceptionConsumer);
	}

	@Override
	protected byte[] gasSensorEventToEvent(GasSensorEvent gasSensorEvent) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(new String(Si7021.HEADER));
		stringBuffer.append(Si7021.SEPARATOR_STRING);
		stringBuffer.append(Si7021.TEMPERATURE_PREFIX);
		stringBuffer.append(gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Si7021.TEMPERATURE_DESCRIPTION)).map(GasSensing::getValue)
				.findFirst().get().intValueExact());
		stringBuffer.append(Si7021.SEPARATOR_STRING);
		stringBuffer.append(Si7021.HUMIDITY_PREFIX);
		stringBuffer.append(gasSensorEvent.getGasSensingList().stream()
				.filter(g -> g.getDescription().equals(Si7021.HUMIDITY_DESCRIPTION)).map(GasSensing::getValue)
				.findFirst().get().intValueExact());
		stringBuffer.append(Si7021.SEPARATOR_STRING);
		return stringBuffer.toString().getBytes();
	}
}
