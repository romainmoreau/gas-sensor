package fr.romainmoreau.gassensor.client.si7021;

public class Si7021 {
	public static final String SENSOR_NAME = "Si7021";
	public static final int CHECKSUM_LENGTH = 0;
	public static final byte[] HEADER = "id:".getBytes();
	public static final String TEMPERATURE_DESCRIPTION = "Temperature";
	public static final String TEMPERATURE_UNIT = "°C";
	public static final String HUMIDITY_DESCRIPTION = "Humidity";
	public static final String HUMIDITY_UNIT = "% RH";
	public static final byte SEPARATOR = ' ';
	public static final String SEPARATOR_STRING = " ";
	public static final String SEPARATOR_REGEX = " ";
	public static final int EVENT_SEPARATOR_COUNT = 3;
	public static final String TEMPERATURE_PREFIX = "tem:";
	public static final String HUMIDITY_PREFIX = "hum:";
}
