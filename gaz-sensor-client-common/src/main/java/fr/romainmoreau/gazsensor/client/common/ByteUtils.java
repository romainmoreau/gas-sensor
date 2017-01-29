package fr.romainmoreau.gazsensor.client.common;

import java.math.BigDecimal;

public class ByteUtils {
	public static BigDecimal highByteLowByteToInt(byte highByte, byte lowByte) {
		return new BigDecimal(highByte * 256 + lowByte);
	}
}
