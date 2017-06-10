package fr.romainmoreau.gassensor.client.common;

import java.util.List;

public interface GasSensorEventAnalyser {
	boolean isContainingAtLeastOneEvent(List<Byte> byteList);

	byte[] getFirstEvent(List<Byte> byteList);
}
