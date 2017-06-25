package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

public class TestGasSensorClient extends AbstractGasSensorClient<TestGasSensorEvent> {
	public TestGasSensorClient(TestGasSensorEventListener testGasSensorEventListener,
			TestGasSensorExceptionHandler testGasSensorExceptionHandler, GasSensorEventAnalyser gasSensorEventAnalyser,
			GasSensorEventValidator gasSensorEventValidator, byte... header) throws IOException {
		super("TEST", TestGasSensorReader::new, testGasSensorEventListener, testGasSensorExceptionHandler,
				gasSensorEventAnalyser, gasSensorEventValidator, header);
	}

	@Override
	protected TestGasSensorEvent eventToGasSensorEvent(byte[] event) {
		return new TestGasSensorEvent(event);
	}
}
