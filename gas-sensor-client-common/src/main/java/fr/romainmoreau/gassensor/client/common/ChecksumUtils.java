package fr.romainmoreau.gassensor.client.common;

import java.nio.ByteBuffer;

public class ChecksumUtils {
	public static byte sum(byte[] event) {
		byte checksum = 0;
		for (int i = 0; i < event.length - 1; i++) {
			checksum += event[i];
		}
		return checksum;
	}

	public static byte[] twoBytesSum(byte[] event) {
		short checksum = 0;
		for (int i = 0; i < event.length - 2; i++) {
			checksum += event[i];
		}
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.putShort(checksum);
		return buffer.array();
	}

	public static byte notSum(byte[] event) {
		return (byte) ~sum(event);
	}
}
