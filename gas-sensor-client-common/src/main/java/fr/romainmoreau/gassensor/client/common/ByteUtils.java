package fr.romainmoreau.gassensor.client.common;

import java.math.BigDecimal;
import java.util.List;

public class ByteUtils {
	public static BigDecimal highByteLowByteToBigDecimal(byte highByte, byte lowByte) {
		return new BigDecimal(unsignedValue(highByte) * 256 + unsignedValue(lowByte));
	}

	public static int unsignedValue(byte signedByte) {
		return signedByte & 0xff;
	}

	public static byte[] getNFirstBytes(int n, List<Byte> byteList) {
		List<Byte> nFirstByteList = byteList.subList(0, n);
		byte[] nFirstBytes = new byte[n];
		for (int i = 0; i < nFirstByteList.size(); i++) {
			nFirstBytes[i] = nFirstByteList.get(i);
		}
		return nFirstBytes;
	}
}
