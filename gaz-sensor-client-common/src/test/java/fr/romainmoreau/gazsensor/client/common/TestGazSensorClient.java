package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;

public class TestGazSensorClient extends AbstractGazSensorClient<TestGazSensorEvent> {
	public TestGazSensorClient(TestGazSensorEventListener testGazSensorEventListener,
			TestGazSensorExceptionHandler testGazSensorExceptionHandler, int length, int checksumLength, byte... header)
			throws IOException {
		super("TEST", TestGazSensorReader::new, testGazSensorEventListener, testGazSensorExceptionHandler, length,
				checksumLength, header);
	}

	@Override
	protected TestGazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new TestGazSensorEvent(event);
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.sum(event) };
	}
}
