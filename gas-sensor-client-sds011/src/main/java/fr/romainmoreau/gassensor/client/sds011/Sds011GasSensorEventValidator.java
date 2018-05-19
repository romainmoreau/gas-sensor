package fr.romainmoreau.gassensor.client.sds011;

import java.nio.ByteBuffer;
import java.util.Arrays;

import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.GasSensorEventValidator;

public class Sds011GasSensorEventValidator implements GasSensorEventValidator {
	@Override
	public boolean isValid(byte[] event) {
		byte[] checksum = Arrays.copyOfRange(event, 8, event.length);
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.put(ChecksumUtils.sum(Arrays.copyOfRange(event, 2, 9)));
		buffer.put(Sds011.TAIL);
		byte[] expectedChecksum = buffer.array();
		return Arrays.equals(expectedChecksum, checksum);
	}
}
