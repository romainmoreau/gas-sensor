package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;

public class TestGazSensorReader implements GazSensorReader {
	public TestGazSensorReader(GazSensorClient<?> gazSensorClient) throws IOException {
	}

	@Override
	public void close() throws IOException {
	}
}
