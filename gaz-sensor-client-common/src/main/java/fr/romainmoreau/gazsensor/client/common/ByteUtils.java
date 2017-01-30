package fr.romainmoreau.gazsensor.client.common;

import java.math.BigDecimal;

public class ByteUtils {
	public static BigDecimal highByteLowByteToBigDecimal(byte highByte, byte lowByte) {
		return new BigDecimal(unsignedValue(highByte) * 256 + unsignedValue(lowByte));
	}

	public static int unsignedValue(byte signedByte) {
		return signedByte & 0xff;
	}
}
