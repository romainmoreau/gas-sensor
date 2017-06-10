package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

public class TestGasSensorClient extends AbstractGasSensorClient<TestGasSensorEvent> {
	public TestGasSensorClient(TestGasSensorEventListener testGasSensorEventListener,
			TestGasSensorExceptionHandler testGasSensorExceptionHandler, GasSensorEventAnalyser gasSensorEventAnalyser,
			int checksumLength, byte... header) throws IOException {
		super("TEST", TestGasSensorReader::new, testGasSensorEventListener, testGasSensorExceptionHandler,
				gasSensorEventAnalyser, checksumLength, header);
	}

	@Override
	protected TestGasSensorEvent eventToGasSensorEvent(byte[] event) {
		return new TestGasSensorEvent(event);
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.sum(event) };
	}
}
