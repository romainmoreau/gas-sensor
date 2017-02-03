package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

import fr.romainmoreau.gassensor.client.common.GasSensorClient;
import fr.romainmoreau.gassensor.client.common.GasSensorReader;

public class TestGasSensorReader implements GasSensorReader {
	public TestGasSensorReader(GasSensorClient<?> gasSensorClient) throws IOException {
	}

	@Override
	public void close() throws IOException {
	}
}
