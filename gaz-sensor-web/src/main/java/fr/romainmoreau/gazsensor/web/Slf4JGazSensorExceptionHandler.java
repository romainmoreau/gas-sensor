package fr.romainmoreau.gazsensor.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gazsensor.client.common.GazSensorExceptionHandler;

@Component
public class Slf4JGazSensorExceptionHandler implements GazSensorExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(Slf4JGazSensorExceptionHandler.class);

	@Override
	public void onIgnoredByte(byte ignoredByte) {
		LOGGER.warn("byte {} ignored", ignoredByte);
	}

	@Override
	public void onReadBytesError(IOException ioException) {
		LOGGER.error("Exception while reading bytes", ioException);
	}
}
