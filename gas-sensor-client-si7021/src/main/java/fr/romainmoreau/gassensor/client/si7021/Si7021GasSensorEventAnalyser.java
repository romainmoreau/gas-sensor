package fr.romainmoreau.gassensor.client.si7021;

import java.util.List;

import fr.romainmoreau.gassensor.client.common.ByteUtils;
import fr.romainmoreau.gassensor.client.common.GasSensorEventAnalyser;

public class Si7021GasSensorEventAnalyser implements GasSensorEventAnalyser {
	@Override
	public boolean isContainingAtLeastOneEvent(List<Byte> byteList) {
		return byteList.stream().filter(b -> b == Si7021.SEPARATOR).count() >= Si7021.EVENT_SEPARATOR_COUNT;
	}

	@Override
	public byte[] getFirstEvent(List<Byte> byteList) {
		int eventLength = 0;
		int separatorCount = 0;
		for (byte b : byteList) {
			eventLength++;
			if (b == Si7021.SEPARATOR) {
				separatorCount++;
			}
			if (separatorCount == Si7021.EVENT_SEPARATOR_COUNT) {
				break;
			}
		}
		return ByteUtils.getNFirstBytes(eventLength, byteList);
	}
}
