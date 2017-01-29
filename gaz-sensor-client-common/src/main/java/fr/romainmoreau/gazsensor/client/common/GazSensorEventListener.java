package fr.romainmoreau.gazsensor.client.common;

public interface GazSensorEventListener<E extends GazSensorEvent> {
	void onGazSensorEvent(String sensorName, E gazSensorEvent);
}
