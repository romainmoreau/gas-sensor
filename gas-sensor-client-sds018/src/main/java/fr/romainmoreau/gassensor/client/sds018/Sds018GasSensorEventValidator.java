package fr.romainmoreau.gassensor.client.sds018;

import java.nio.ByteBuffer;
import java.util.Arrays;

import fr.romainmoreau.gassensor.client.common.ChecksumUtils;
import fr.romainmoreau.gassensor.client.common.GasSensorEventValidator;

public class Sds018GasSensorEventValidator implements GasSensorEventValidator {
	@Override
	public boolean isValid(byte[] event) {
		byte[] checksum = Arrays.copyOfRange(event, 8, event.length);
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.put(ChecksumUtils.sum(Arrays.copyOfRange(event, 2, 9)));
		buffer.put(Sds018.TAIL);
		byte[] expectedChecksum = buffer.array();
		return Arrays.equals(expectedChecksum, checksum);
	}
}
