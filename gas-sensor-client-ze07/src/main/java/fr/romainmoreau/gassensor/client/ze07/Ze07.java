package fr.romainmoreau.gassensor.client.ze07;

import java.math.BigDecimal;

public class Ze07 {
	public static final String SENSOR_NAME = "ZE07";
	public static final int EVENT_LENGTH = 9;
	public static final int CHECKSUM_LENGTH = 1;
	public static final byte[] HEADER = { (byte) -1, (byte) 4, (byte) 3, (byte) 1 };
	public static final String CO_DESCRIPTION = "CO";
	public static final String CO_UNIT = "ppm";
	public static final BigDecimal CO_MULTIPLICAND = new BigDecimal("0.1");
}
