package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

public class TestGazSensorClient extends AbstractGazSensorClient<TestGazSensorEvent> {
	private List<Byte> ignoredByteList;

	public TestGazSensorClient(TestGazSensorEventListener testGazSensorEventListener, int length, int checksumLength,
			byte... header) throws IOException {
		super("TEST", TestGazSensorReader::new, testGazSensorEventListener, length, checksumLength, header);
		ignoredByteList = new ArrayList<>();
	}

	@Override
	public void onIgnoredByte(byte ignoredByte) {
		ignoredByteList.add(ignoredByte);
	}

	@Override
	protected TestGazSensorEvent eventToGazSensorEvent(byte[] event) {
		return new TestGazSensorEvent(event);
	}

	@Override
	protected byte[] calculateChecksum(byte[] event) {
		return new byte[] { ChecksumUtils.sum(event) };
	}

	public List<Byte> getIgnoredByteList() {
		return ignoredByteList;
	}

	public void assertIgnoredByteListEquals(Byte... ignoredBytes) {
		Assert.assertArrayEquals(ignoredBytes, ignoredByteList.toArray(new Byte[ignoredByteList.size()]));
	}
}
