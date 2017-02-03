package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.AbstractGasSensorClient;
import fr.romainmoreau.gassensor.client.common.ChecksumUtils;

public class TestGasSensorClient extends AbstractGasSensorClient<TestGasSensorEvent> {
	public TestGasSensorClient(TestGasSensorEventListener testGasSensorEventListener,
			TestGasSensorExceptionHandler testGasSensorExceptionHandler, int length, int checksumLength, byte... header)
			throws IOException {
		super("TEST", TestGasSensorReader::new, testGasSensorEventListener, testGasSensorExceptionHandler, length,
				checksumLength, header);
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
