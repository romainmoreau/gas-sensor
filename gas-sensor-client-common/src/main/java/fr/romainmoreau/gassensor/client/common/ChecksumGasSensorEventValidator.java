package fr.romainmoreau.gassensor.client.common;

import java.util.Arrays;
import java.util.function.Function;

public class ChecksumGasSensorEventValidator implements GasSensorEventValidator {
	private final int checksumLength;

	private final Function<byte[], byte[]> checksumFunction;

	public ChecksumGasSensorEventValidator(int checksumLength, Function<byte[], byte[]> checksumFunction) {
		this.checksumLength = checksumLength;
		this.checksumFunction = checksumFunction;
	}

	private byte[] getChecksum(byte[] event) {
		return Arrays.copyOfRange(event, event.length - checksumLength, event.length);
	}

	@Override
	public boolean isValid(byte[] event) {
		byte[] checksum = getChecksum(event);
		byte[] expectedChecksum = checksumFunction.apply(event);
		return Arrays.equals(expectedChecksum, checksum);
	}
}
