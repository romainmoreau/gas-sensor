package fr.romainmoreau.gassensor.client.mhz19;

public class MhZ19 {
	public static final String SENSOR_NAME = "MH-Z19";
	public static final int EVENT_LENGTH = 9;
	public static final int CHECKSUM_LENGTH = 1;
	public static final byte[] HEADER = { (byte) -1, (byte) 134 };
	public static final String CO2_DESCRIPTION = "CO2";
	public static final String CO2_UNIT = "ppm";
}
