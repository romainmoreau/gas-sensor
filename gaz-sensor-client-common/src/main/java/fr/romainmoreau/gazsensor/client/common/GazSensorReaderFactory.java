package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;

public interface GazSensorReaderFactory {
	GazSensorReader construct(GazSensorClient<?> gazSensorClient) throws IOException;
}
