package fr.romainmoreau.gazsensor.client.common;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GenericGazSensorEvent implements GazSensorEvent {
	private final LocalDateTime localDateTime;

	private final List<GazSensing> gazSensingList;

	public GenericGazSensorEvent(GazSensing... gazSensings) {
		localDateTime = LocalDateTime.now();
		gazSensingList = Collections.unmodifiableList(Arrays.asList(gazSensings));
	}

	@Override
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public List<GazSensing> getGazSensingList() {
		return gazSensingList;
	}

	@Override
	public String toString() {
		return gazSensingList.stream().map(GazSensing::toString).collect(Collectors.joining("\n"));
	}
}
