package fr.romainmoreau.gassensor.client.pmsa003;

public class Pmsa003 {
	public static final String SENSOR_NAME = "PMSA003";
	public static final int EVENT_LENGTH = 32;
	public static final int CHECKSUM_LENGTH = 2;
	public static final byte[] HEADER = { (byte) 0x42, (byte) 0x4d };
	public static final String PM1_0_DESCRIPTION = "PM1.0";
	public static final String PM2_5_DESCRIPTION = "PM2.5";
	public static final String PM10_DESCRIPTION = "PM10";
	public static final String PM_UNIT = "ug/m3";
}
