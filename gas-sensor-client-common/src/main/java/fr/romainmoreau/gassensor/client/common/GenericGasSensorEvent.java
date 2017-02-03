package fr.romainmoreau.gassensor.client.common;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GenericGasSensorEvent implements GasSensorEvent {
	private final LocalDateTime localDateTime;

	private final List<GasSensing> gasSensingList;

	public GenericGasSensorEvent(GasSensing... gasSensings) {
		localDateTime = LocalDateTime.now();
		gasSensingList = Collections.unmodifiableList(Arrays.asList(gasSensings));
	}

	@Override
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public List<GasSensing> getGasSensingList() {
		return gasSensingList;
	}

	@Override
	public String toString() {
		return gasSensingList.stream().map(GasSensing::toString).collect(Collectors.joining("\n"));
	}
}
