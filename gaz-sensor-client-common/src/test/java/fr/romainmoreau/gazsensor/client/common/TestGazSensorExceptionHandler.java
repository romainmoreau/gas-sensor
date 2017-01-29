package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

public class TestGazSensorExceptionHandler implements GazSensorExceptionHandler {
	private List<Byte> ignoredByteList;

	public TestGazSensorExceptionHandler() {
		ignoredByteList = new ArrayList<>();
	}

	@Override
	public void onReadBytesError(IOException ioException) {
	}

	@Override
	public void onIgnoredByte(byte ignoredByte) {
		ignoredByteList.add(ignoredByte);
	}

	public List<Byte> getIgnoredByteList() {
		return ignoredByteList;
	}

	public void assertIgnoredByteListEquals(Byte... ignoredBytes) {
		Assert.assertArrayEquals(ignoredBytes, ignoredByteList.toArray(new Byte[ignoredByteList.size()]));
	}
}
