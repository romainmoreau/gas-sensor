package fr.romainmoreau.gazsensor.client.common;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class AbstractGazSensorClientTest {
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyListener() throws IOException {
		TestGazSensorExceptionHandler testGazSensorExceptionHandler = new TestGazSensorExceptionHandler();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(null, testGazSensorExceptionHandler, 10,
				1, (byte) 1, (byte) 2)) {
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeaderTooLong() throws IOException {
		TestGazSensorEventListener testGazSensorEventListener = new TestGazSensorEventListener();
		TestGazSensorExceptionHandler testGazSensorExceptionHandler = new TestGazSensorExceptionHandler();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(testGazSensorEventListener,
				testGazSensorExceptionHandler, 1, 10, (byte) 1, (byte) 2)) {
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyExceptionHandler() throws IOException {
		TestGazSensorEventListener testGazSensorEventListener = new TestGazSensorEventListener();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(testGazSensorEventListener, null, 10, 1,
				(byte) 1, (byte) 2)) {
		}
	}

	@Test
	public void testOneEvent() throws IOException {
		TestGazSensorEventListener testGazSensorEventListener = new TestGazSensorEventListener();
		TestGazSensorExceptionHandler testGazSensorExceptionHandler = new TestGazSensorExceptionHandler();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(testGazSensorEventListener,
				testGazSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45 });
			Assert.assertEquals(1, testGazSensorEventListener.getTestGazSensorEventList().size());
			testGazSensorEventListener.getTestGazSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 3,
					(byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 45);
			Assert.assertEquals(0, testGazSensorExceptionHandler.getIgnoredByteList().size());
		}
	}

	@Test
	public void testOneEventSplitted() throws IOException {
		TestGazSensorEventListener testGazSensorEventListener = new TestGazSensorEventListener();
		TestGazSensorExceptionHandler testGazSensorExceptionHandler = new TestGazSensorExceptionHandler();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(testGazSensorEventListener,
				testGazSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 2 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 3 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 4 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 5 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 6 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 7 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 8 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 9 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 45 });
			Assert.assertEquals(1, testGazSensorEventListener.getTestGazSensorEventList().size());
			testGazSensorEventListener.getTestGazSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 3,
					(byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 45);
			Assert.assertEquals(0, testGazSensorExceptionHandler.getIgnoredByteList().size());
		}
	}

	@Test
	public void testTwoEvents() throws IOException {
		TestGazSensorEventListener testGazSensorEventListener = new TestGazSensorEventListener();
		TestGazSensorExceptionHandler testGazSensorExceptionHandler = new TestGazSensorExceptionHandler();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(testGazSensorEventListener,
				testGazSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(2, testGazSensorEventListener.getTestGazSensorEventList().size());
			testGazSensorEventListener.getTestGazSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 3,
					(byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 45);
			testGazSensorEventListener.getTestGazSensorEventList().get(1).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(0, testGazSensorExceptionHandler.getIgnoredByteList().size());
		}
	}

	@Test
	public void testTwoEventsInOneUpdate() throws IOException {
		TestGazSensorEventListener testGazSensorEventListener = new TestGazSensorEventListener();
		TestGazSensorExceptionHandler testGazSensorExceptionHandler = new TestGazSensorExceptionHandler();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(testGazSensorEventListener,
				testGazSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45, (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(2, testGazSensorEventListener.getTestGazSensorEventList().size());
			testGazSensorEventListener.getTestGazSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 3,
					(byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 45);
			testGazSensorEventListener.getTestGazSensorEventList().get(1).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(0, testGazSensorExceptionHandler.getIgnoredByteList().size());
		}
	}

	@Test
	public void testTwoEventsWithOneBadChecksum() throws IOException {
		TestGazSensorEventListener testGazSensorEventListener = new TestGazSensorEventListener();
		TestGazSensorExceptionHandler testGazSensorExceptionHandler = new TestGazSensorExceptionHandler();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(testGazSensorEventListener,
				testGazSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 44 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(1, testGazSensorEventListener.getTestGazSensorEventList().size());
			testGazSensorEventListener.getTestGazSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(10, testGazSensorExceptionHandler.getIgnoredByteList().size());
			testGazSensorExceptionHandler.assertIgnoredByteListEquals((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5,
					(byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 44);
		}
	}

	@Test
	public void testTwoEventsWithOneMissingByte() throws IOException {
		TestGazSensorEventListener testGazSensorEventListener = new TestGazSensorEventListener();
		TestGazSensorExceptionHandler testGazSensorExceptionHandler = new TestGazSensorExceptionHandler();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(testGazSensorEventListener,
				testGazSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 4, (byte) 5, (byte) 6, (byte) 7,
					(byte) 8, (byte) 9, (byte) 45 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(1, testGazSensorEventListener.getTestGazSensorEventList().size());
			testGazSensorEventListener.getTestGazSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(9, testGazSensorExceptionHandler.getIgnoredByteList().size());
			testGazSensorExceptionHandler.assertIgnoredByteListEquals((byte) 1, (byte) 2, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45);
		}
	}

	@Test
	public void testTwoEventsWithOneMissingByteInHeader() throws IOException {
		TestGazSensorEventListener testGazSensorEventListener = new TestGazSensorEventListener();
		TestGazSensorExceptionHandler testGazSensorExceptionHandler = new TestGazSensorExceptionHandler();
		try (TestGazSensorClient testGazSensorClient = new TestGazSensorClient(testGazSensorEventListener,
				testGazSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7,
					(byte) 8, (byte) 9, (byte) 45 });
			testGazSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(1, testGazSensorEventListener.getTestGazSensorEventList().size());
			testGazSensorEventListener.getTestGazSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(9, testGazSensorExceptionHandler.getIgnoredByteList().size());
			testGazSensorExceptionHandler.assertIgnoredByteListEquals((byte) 1, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45);
		}
	}
}
