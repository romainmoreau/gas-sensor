package fr.romainmoreau.gassensor.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.romainmoreau.gassensor.datamodel.GasSensingInterval;
import fr.romainmoreau.gassensor.datamodel.GasSensingUpdates;
import fr.romainmoreau.gassensor.datamodel.GasSensingUpdatesRange;
import fr.romainmoreau.gassensor.web.data.GasSensingIntervalRepository;
import fr.romainmoreau.gassensor.web.data.GasSensingUpdateRepository;
import fr.romainmoreau.gassensor.web.data.GasSensingUpdatesRangeRepository;

@RestController
public class GasSensingUpdateController {
	@Autowired
	private GasSensingUpdateRepository gasSensingUpdateRepository;

	@Autowired
	private GasSensingIntervalRepository gasSensingIntervalRepository;

	@Autowired
	private GasSensingUpdatesRangeRepository gasSensingUpdatesRangeRepository;

	@GetMapping(path = "/ranges", produces = { "application/json" })
	public List<GasSensingUpdatesRange> getRanges() {
		return gasSensingUpdatesRangeRepository.findAll();
	}

	@GetMapping(path = "/updates/{sensorName}/{description}/{beginning}/{end}", produces = { "application/json" })
	public GasSensingUpdates getUpdates(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, @PathVariable LocalDateTime beginning, @PathVariable LocalDateTime end) {
		return new GasSensingUpdates(
				gasSensingUpdateRepository.findBySensorNameAndDescriptionAndUnitAndLocalDateTimeBetweenOrderByIdAsc(
						sensorName, description, unit, beginning, end),
				gasSensingUpdateRepository
						.findFirstBySensorNameAndDescriptionAndUnitAndLocalDateTimeLessThanOrderByIdDesc(sensorName,
								description, unit, beginning));
	}

	@GetMapping(path = "/intervals/{description}/", produces = { "application/json" })
	public List<GasSensingInterval> getIntervals(@PathVariable String description, @RequestParam String unit) {
		return gasSensingIntervalRepository.findByDescriptionAndUnit(description, unit);
	}
}
