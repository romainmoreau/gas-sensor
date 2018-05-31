package fr.romainmoreau.gassensor.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.romainmoreau.gassensor.datamodel.GasSensingInterval;
import fr.romainmoreau.gassensor.datamodel.GasSensingUpdate;
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

	@RequestMapping(path = "/ranges", method = RequestMethod.GET, produces = { "application/json" })
	public List<GasSensingUpdatesRange> getRanges() {
		return gasSensingUpdatesRangeRepository.findAll();
	}

	@RequestMapping(path = "/updates/{sensorName}/{description}/{beginning}/{end}", method = RequestMethod.GET, produces = {
			"application/json" })
	public List<GasSensingUpdate> getUpdates(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, @PathVariable LocalDateTime beginning, @PathVariable LocalDateTime end) {
		return gasSensingUpdateRepository.findBySensorNameAndDescriptionAndUnitAndLocalDateTimeBetweenOrderByIdDesc(
				sensorName, description, unit, beginning, end);
	}

	@RequestMapping(path = "/intervals/{description}/", method = RequestMethod.GET, produces = { "application/json" })
	public List<GasSensingInterval> getIntervals(@PathVariable String description, @RequestParam String unit) {
		return gasSensingIntervalRepository.findByDescriptionAndUnit(description, unit);
	}
}
