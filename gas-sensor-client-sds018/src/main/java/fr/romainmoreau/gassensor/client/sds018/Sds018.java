package fr.romainmoreau.gassensor.client.sds018;

public class Sds018 {
	public static final String SENSOR_NAME = "SDS018";
	public static final int EVENT_LENGTH = 10;
	public static final byte[] HEADER = { (byte) 0xaa, (byte) 0xc0 };
	public static final byte TAIL = (byte) 0xab;
	public static final String PM2_5_DESCRIPTION = "PM2.5";
	public static final String PM10_DESCRIPTION = "PM10";
	public static final String PM_UNIT = "ug/m3";
}
