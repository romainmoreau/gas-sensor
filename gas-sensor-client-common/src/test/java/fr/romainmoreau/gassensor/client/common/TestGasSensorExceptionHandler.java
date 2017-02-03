package fr.romainmoreau.gassensor.client.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;

public class TestGasSensorExceptionHandler implements GasSensorExceptionHandler {
	private List<Byte> ignoredByteList;

	public TestGasSensorExceptionHandler() {
		ignoredByteList = new ArrayList<>();
	}

	@Override
	public void onReadBytesError(Exception exception) {
	}

	@Override
	public void onIgnoredByte(byte ignoredByte, String cause) {
		ignoredByteList.add(ignoredByte);
	}

	public List<Byte> getIgnoredByteList() {
		return ignoredByteList;
	}

	public void assertIgnoredByteListEquals(Byte... ignoredBytes) {
		Assert.assertArrayEquals(ignoredBytes, ignoredByteList.toArray(new Byte[ignoredByteList.size()]));
	}
}
