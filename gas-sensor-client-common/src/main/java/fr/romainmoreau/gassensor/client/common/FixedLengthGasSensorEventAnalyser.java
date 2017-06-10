package fr.romainmoreau.gassensor.client.common;

import java.util.List;

public class FixedLengthGasSensorEventAnalyser implements GasSensorEventAnalyser {
	private final int length;

	public FixedLengthGasSensorEventAnalyser(int length) {
		this.length = length;
	}

	@Override
	public boolean isContainingAtLeastOneEvent(List<Byte> byteList) {
		return byteList.size() >= length;
	}

	@Override
	public byte[] getFirstEvent(List<Byte> byteList) {
		return ByteUtils.getNFirstBytes(length, byteList);
	}
}
