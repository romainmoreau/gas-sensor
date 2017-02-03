package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

public interface GasSensorReaderFactory<E extends GasSensorEvent> {
	GasSensorReader construct(GasSensorClient<E> gasSensorClient) throws IOException;
}
