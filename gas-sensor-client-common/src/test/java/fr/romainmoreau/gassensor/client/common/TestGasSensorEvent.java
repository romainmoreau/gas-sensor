package fr.romainmoreau.gassensor.client.common;

import org.junit.Assert;

import fr.romainmoreau.gassensor.client.common.GenericGasSensorEvent;

public class TestGasSensorEvent extends GenericGasSensorEvent {
	private byte[] event;

	public TestGasSensorEvent(byte[] event) {
		this.event = event;
	}

	public void assertEquals(byte... event) {
		Assert.assertArrayEquals(event, this.event);
	}
}
