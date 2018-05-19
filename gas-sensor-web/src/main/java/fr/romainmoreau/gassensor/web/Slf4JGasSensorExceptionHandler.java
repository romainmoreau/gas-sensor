package fr.romainmoreau.gassensor.web;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.client.common.GasSensorExceptionHandler;

@Component
public class Slf4JGasSensorExceptionHandler implements GasSensorExceptionHandler, Consumer<Exception> {
	private static final Logger LOGGER = LoggerFactory.getLogger(Slf4JGasSensorExceptionHandler.class);

	@Override
	public void onIgnoredByte(String sensorName, byte ignoredByte, String cause) {
		LOGGER.warn("[{}] byte {} ignored because {}", sensorName, ignoredByte, cause);
	}

	@Override
	public void onReadBytesError(String sensorName, Exception exception) {
		LOGGER.error("[{}] Exception while reading bytes", sensorName, exception);
	}

	@Override
	public void accept(Exception exception) {
		LOGGER.error("Exception", exception);
	}
}
