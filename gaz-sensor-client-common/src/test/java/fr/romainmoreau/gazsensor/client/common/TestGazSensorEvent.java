package fr.romainmoreau.gazsensor.client.common;

import org.junit.Assert;

public class TestGazSensorEvent extends GenericGazSensorEvent {
	private byte[] event;

	public TestGazSensorEvent(byte[] event) {
		this.event = event;
	}

	public void assertEquals(byte... event) {
		Assert.assertArrayEquals(event, this.event);
	}
}
