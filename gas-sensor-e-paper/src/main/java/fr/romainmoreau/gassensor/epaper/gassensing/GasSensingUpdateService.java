package fr.romainmoreau.gassensor.epaper.gassensing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.romainmoreau.gassensor.datamodel.GasSensingUpdates;
import fr.romainmoreau.gassensor.datamodel.GasSensingUpdatesRange;

@Service
public class GasSensingUpdateService {
	private static final String RANGES_URL = "/ranges";
	private static final String UPDATES_URL = "/updates/{sensorName}/{description}/{beginning}/{end}?unit={unit}";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private GasSensingProperties gasSensingProperties;

	public LastGasSensingUpdates getLastGasSensingUpdates() {
		LastGasSensingUpdates lastGasSensingUpdates = new LastGasSensingUpdates();
		lastGasSensingUpdates.setLocalDateTime(LocalDateTime.now());
		LocalDateTime maxLocalDateTime = lastGasSensingUpdates.getLocalDateTime()
				.minus(gasSensingProperties.getMaxMilliseconds(), ChronoUnit.MILLIS);
		lastGasSensingUpdates.setLastGasSensingUpdateList(Arrays
				.asList(restTemplate
						.getForObject(gasSensingProperties.getBaseUrl() + RANGES_URL, GasSensingUpdatesRange[].class))
				.stream()
				.map(r -> restTemplate.getForObject(gasSensingProperties.getBaseUrl() + UPDATES_URL,
						GasSensingUpdates.class, r.getSensorName(), r.getDescription(), maxLocalDateTime,
						lastGasSensingUpdates.getLocalDateTime(), r.getUnit()))
				.map(u -> u.getPeriodUpdates().size() > 0 ? u.getPeriodUpdates().get(u.getPeriodUpdates().size() - 1)
						: u.getFirstOutOfPeriodUpdate())
				.filter(Objects::nonNull).collect(Collectors.toList()));
		return lastGasSensingUpdates;
	}
}
