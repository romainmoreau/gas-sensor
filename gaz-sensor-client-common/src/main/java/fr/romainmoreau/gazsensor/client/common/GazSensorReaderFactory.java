package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;

public interface GazSensorReaderFactory<E extends GazSensorEvent> {
	GazSensorReader construct(GazSensorClient<E> gazSensorClient) throws IOException;
}
