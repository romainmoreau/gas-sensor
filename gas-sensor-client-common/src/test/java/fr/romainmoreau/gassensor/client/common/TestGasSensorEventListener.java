package fr.romainmoreau.gassensor.client.common;

import java.util.ArrayList;
import java.util.List;

public class TestGasSensorEventListener implements GasSensorEventListener<TestGasSensorEvent> {
	private final List<TestGasSensorEvent> testGasSensorEventList;

	public TestGasSensorEventListener() {
		testGasSensorEventList = new ArrayList<>();
	}

	@Override
	public void onGasSensorEvent(String sensorName, TestGasSensorEvent gasSensorEvent) {
		testGasSensorEventList.add(gasSensorEvent);
	}

	public List<TestGasSensorEvent> getTestGasSensorEventList() {
		return testGasSensorEventList;
	}
}
