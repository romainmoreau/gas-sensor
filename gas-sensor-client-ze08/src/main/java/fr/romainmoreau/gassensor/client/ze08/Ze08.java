package fr.romainmoreau.gassensor.client.ze08;

public class Ze08 {
	public static final String SENSOR_NAME = "ZE08";
	public static final int EVENT_LENGTH = 9;
	public static final int CHECKSUM_LENGTH = 1;
	public static final byte[] HEADER = { (byte) -1, (byte) 23, (byte) 4, (byte) 0 };
	public static final String CH2O_DESCRIPTION = "CH2O";
	public static final String CH2O_UNIT = "ppb";
}
