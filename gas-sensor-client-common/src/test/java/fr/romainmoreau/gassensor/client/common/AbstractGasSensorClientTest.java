package fr.romainmoreau.gassensor.client.common;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class AbstractGasSensorClientTest {
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyListener() throws IOException {
		TestGasSensorExceptionHandler testGasSensorExceptionHandler = new TestGasSensorExceptionHandler();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(null, testGasSensorExceptionHandler, 10,
				1, (byte) 1, (byte) 2)) {
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHeaderTooLong() throws IOException {
		TestGasSensorEventListener testGasSensorEventListener = new TestGasSensorEventListener();
		TestGasSensorExceptionHandler testGasSensorExceptionHandler = new TestGasSensorExceptionHandler();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(testGasSensorEventListener,
				testGasSensorExceptionHandler, 1, 10, (byte) 1, (byte) 2)) {
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyExceptionHandler() throws IOException {
		TestGasSensorEventListener testGasSensorEventListener = new TestGasSensorEventListener();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(testGasSensorEventListener, null, 10, 1,
				(byte) 1, (byte) 2)) {
		}
	}

	@Test
	public void testOneEvent() throws IOException {
		TestGasSensorEventListener testGasSensorEventListener = new TestGasSensorEventListener();
		TestGasSensorExceptionHandler testGasSensorExceptionHandler = new TestGasSensorExceptionHandler();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(testGasSensorEventListener,
				testGasSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45 });
			Assert.assertEquals(1, testGasSensorEventListener.getTestGasSensorEventList().size());
			testGasSensorEventListener.getTestGasSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 3,
					(byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 45);
			Assert.assertEquals(0, testGasSensorExceptionHandler.getIgnoredByteList().size());
		}
	}

	@Test
	public void testOneEventSplitted() throws IOException {
		TestGasSensorEventListener testGasSensorEventListener = new TestGasSensorEventListener();
		TestGasSensorExceptionHandler testGasSensorExceptionHandler = new TestGasSensorExceptionHandler();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(testGasSensorEventListener,
				testGasSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 2 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 3 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 4 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 5 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 6 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 7 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 8 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 9 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 45 });
			Assert.assertEquals(1, testGasSensorEventListener.getTestGasSensorEventList().size());
			testGasSensorEventListener.getTestGasSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 3,
					(byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 45);
			Assert.assertEquals(0, testGasSensorExceptionHandler.getIgnoredByteList().size());
		}
	}

	@Test
	public void testTwoEvents() throws IOException {
		TestGasSensorEventListener testGasSensorEventListener = new TestGasSensorEventListener();
		TestGasSensorExceptionHandler testGasSensorExceptionHandler = new TestGasSensorExceptionHandler();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(testGasSensorEventListener,
				testGasSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(2, testGasSensorEventListener.getTestGasSensorEventList().size());
			testGasSensorEventListener.getTestGasSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 3,
					(byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 45);
			testGasSensorEventListener.getTestGasSensorEventList().get(1).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(0, testGasSensorExceptionHandler.getIgnoredByteList().size());
		}
	}

	@Test
	public void testTwoEventsInOneUpdate() throws IOException {
		TestGasSensorEventListener testGasSensorEventListener = new TestGasSensorEventListener();
		TestGasSensorExceptionHandler testGasSensorExceptionHandler = new TestGasSensorExceptionHandler();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(testGasSensorEventListener,
				testGasSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45, (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(2, testGasSensorEventListener.getTestGasSensorEventList().size());
			testGasSensorEventListener.getTestGasSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 3,
					(byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 45);
			testGasSensorEventListener.getTestGasSensorEventList().get(1).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(0, testGasSensorExceptionHandler.getIgnoredByteList().size());
		}
	}

	@Test
	public void testTwoEventsWithOneBadChecksum() throws IOException {
		TestGasSensorEventListener testGasSensorEventListener = new TestGasSensorEventListener();
		TestGasSensorExceptionHandler testGasSensorExceptionHandler = new TestGasSensorExceptionHandler();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(testGasSensorEventListener,
				testGasSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 44 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(1, testGasSensorEventListener.getTestGasSensorEventList().size());
			testGasSensorEventListener.getTestGasSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(10, testGasSensorExceptionHandler.getIgnoredByteList().size());
			testGasSensorExceptionHandler.assertIgnoredByteListEquals((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5,
					(byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 44);
		}
	}

	@Test
	public void testTwoEventsWithOneMissingByte() throws IOException {
		TestGasSensorEventListener testGasSensorEventListener = new TestGasSensorEventListener();
		TestGasSensorExceptionHandler testGasSensorExceptionHandler = new TestGasSensorExceptionHandler();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(testGasSensorEventListener,
				testGasSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 4, (byte) 5, (byte) 6, (byte) 7,
					(byte) 8, (byte) 9, (byte) 45 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(1, testGasSensorEventListener.getTestGasSensorEventList().size());
			testGasSensorEventListener.getTestGasSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(9, testGasSensorExceptionHandler.getIgnoredByteList().size());
			testGasSensorExceptionHandler.assertIgnoredByteListEquals((byte) 1, (byte) 2, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45);
		}
	}

	@Test
	public void testTwoEventsWithOneMissingByteInHeader() throws IOException {
		TestGasSensorEventListener testGasSensorEventListener = new TestGasSensorEventListener();
		TestGasSensorExceptionHandler testGasSensorExceptionHandler = new TestGasSensorExceptionHandler();
		try (TestGasSensorClient testGasSensorClient = new TestGasSensorClient(testGasSensorEventListener,
				testGasSensorExceptionHandler, 10, 1, (byte) 1, (byte) 2)) {
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7,
					(byte) 8, (byte) 9, (byte) 45 });
			testGasSensorClient.onReadBytes(new byte[] { (byte) 1, (byte) 2, (byte) 9, (byte) 8, (byte) 7, (byte) 6,
					(byte) 5, (byte) 4, (byte) 3, (byte) 45 });
			Assert.assertEquals(1, testGasSensorEventListener.getTestGasSensorEventList().size());
			testGasSensorEventListener.getTestGasSensorEventList().get(0).assertEquals((byte) 1, (byte) 2, (byte) 9,
					(byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 45);
			Assert.assertEquals(9, testGasSensorExceptionHandler.getIgnoredByteList().size());
			testGasSensorExceptionHandler.assertIgnoredByteListEquals((byte) 1, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
					(byte) 7, (byte) 8, (byte) 9, (byte) 45);
		}
	}
}
