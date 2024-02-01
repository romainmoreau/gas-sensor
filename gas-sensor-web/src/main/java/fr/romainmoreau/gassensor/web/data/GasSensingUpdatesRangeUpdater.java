package fr.romainmoreau.gassensor.web.data;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.romainmoreau.gassensor.datamodel.GasSensingUpdatesRange;

@Component
public class GasSensingUpdatesRangeUpdater {
	private static final Logger LOGGER = LoggerFactory.getLogger(GasSensingUpdatesRangeUpdater.class);

	@Autowired
	private GasSensingUpdateRepository gasSensingUpdateRepository;

	@Autowired
	private GasSensingUpdatesRangeRepository gasSensingUpdatesRangeRepository;

	@Scheduled(fixedDelay = 60000)
	@Transactional
	public void updateGasSensingUpdatesRanges() {
		LOGGER.info("Updating gas sensing updates ranges");
		List<Object[]> distinctSensorNameDescriptionUnitWithRangeList = gasSensingUpdateRepository
				.findAllDistinctSensorNameDescriptionUnitWithRange();
		LOGGER.info("{} gas sensing updates ranges found", distinctSensorNameDescriptionUnitWithRangeList.size());
		gasSensingUpdatesRangeRepository.deleteAllInBatch();
		distinctSensorNameDescriptionUnitWithRangeList.stream()
				.map(this::distinctSensorNameDescriptionUnitWithRangesToGasSensingUpdatesRange)
				.forEach(gasSensingUpdatesRangeRepository::save);
	}

	private GasSensingUpdatesRange distinctSensorNameDescriptionUnitWithRangesToGasSensingUpdatesRange(
			Object[] distinctSensorNameDescriptionUnitWithRange) {
		GasSensingUpdatesRange gasSensingUpdatesRange = new GasSensingUpdatesRange();
		gasSensingUpdatesRange.setDescription((String) distinctSensorNameDescriptionUnitWithRange[1]);
		gasSensingUpdatesRange.setMaxLocalDateTime((LocalDateTime) distinctSensorNameDescriptionUnitWithRange[4]);
		gasSensingUpdatesRange.setMinLocalDateTime((LocalDateTime) distinctSensorNameDescriptionUnitWithRange[3]);
		gasSensingUpdatesRange.setSensorName((String) distinctSensorNameDescriptionUnitWithRange[0]);
		gasSensingUpdatesRange.setUnit((String) distinctSensorNameDescriptionUnitWithRange[2]);
		return gasSensingUpdatesRange;
	}
}
