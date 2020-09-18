package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

public class TestGasSensorReader implements GasSensorReader {
	public TestGasSensorReader(GasSensorClient<?> gasSensorClient) throws IOException {
	}

	@Override
	public void close() throws IOException {
	}
}
