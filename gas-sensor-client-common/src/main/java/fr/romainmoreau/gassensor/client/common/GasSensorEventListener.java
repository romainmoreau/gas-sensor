package fr.romainmoreau.gassensor.client.common;

public interface GasSensorEventListener<E extends GasSensorEvent> {
	void onGasSensorEvent(String sensorName, E gasSensorEvent);
}
