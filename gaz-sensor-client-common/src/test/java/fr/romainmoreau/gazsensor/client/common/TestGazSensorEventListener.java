package fr.romainmoreau.gazsensor.client.common;

import java.util.ArrayList;
import java.util.List;

public class TestGazSensorEventListener implements GazSensorEventListener<TestGazSensorEvent> {
	private final List<TestGazSensorEvent> testGazSensorEventList;

	public TestGazSensorEventListener() {
		testGazSensorEventList = new ArrayList<>();
	}

	@Override
	public void onGazSensorEvent(String sensorName, TestGazSensorEvent gazSensorEvent) {
		testGazSensorEventList.add(gazSensorEvent);
	}

	public List<TestGazSensorEvent> getTestGazSensorEventList() {
		return testGazSensorEventList;
	}
}
