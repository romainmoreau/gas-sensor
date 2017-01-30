package fr.romainmoreau.gazsensor.client.zph01;

import java.math.BigDecimal;

public class Zph01 {
	public static final String SENSOR_NAME = "ZPH01";
	public static final int EVENT_LENGTH = 9;
	public static final int CHECKSUM_LENGTH = 1;
	public static final byte[] HEADER = { (byte) -1, (byte) 24, (byte) 0 };
	public static final String PM2_5_DESCRIPTION = "PM2.5";
	public static final String PM2_5_UNIT = "% (Low pulse rate)";
	public static final int PM2_5_MULTIPLICAND = 100;
	public static final BigDecimal PM2_5_DIVISOR = new BigDecimal(PM2_5_MULTIPLICAND);
	public static final String VOC_DESCRIPTION = "VOC";
	public static final String VOC_UNIT = "(0-3)";
}
