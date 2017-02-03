package fr.romainmoreau.gassensor.client.zh03a;

public class Zh03A {
	public static final String SENSOR_NAME = "ZH03A";
	public static final int EVENT_LENGTH = 24;
	public static final int CHECKSUM_LENGTH = 2;
	public static final byte[] HEADER = { (byte) 66, (byte) 77, (byte) 0, (byte) 20 };
	public static final String PM1_0_DESCRIPTION = "PM1.0";
	public static final String PM2_5_DESCRIPTION = "PM2.5";
	public static final String PM10_DESCRIPTION = "PM10";
	public static final String PM_UNIT = "ug/m3";
}
