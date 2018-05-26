package fr.romainmoreau.gassensor.web.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.romainmoreau.gassensor.web.data.GasSensingUpdatesRange;
import fr.romainmoreau.gassensor.web.data.GasSensingUpdatesRangeRepository;

@Controller
public class GasSensingUpdateChartController {
	@Autowired
	private GasSensingUpdatesRangeRepository gasSensingUpdatesRangeRepository;

	@RequestMapping(value = "/charts", method = RequestMethod.GET)
	private String getGasSensors(Model model) {
		model.addAttribute("sensorNameGasSensingUpdatesRangesMap", gasSensingUpdatesRangeRepository.findAll().stream()
				.collect(Collectors.groupingBy(GasSensingUpdatesRange::getSensorName)));
		return "gassensors";
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/{amountToSubtract}-{chronoUnit}", method = RequestMethod.GET)
	private String getGasSensingUpdateChart(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, @PathVariable int amountToSubtract, @PathVariable ChronoUnit chronoUnit,
			Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit, amountToSubtract + " " + chronoUnit.name(),
				LocalDateTime.now().minus(amountToSubtract, chronoUnit), LocalDateTime.now(), false, true, model);
	}

	@RequestMapping(value = "/simplechart/{sensorName}/{description}/{amountToSubtract}-{chronoUnit}", method = RequestMethod.GET)
	private String getGasSensingUpdateSimpleChart(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, @PathVariable int amountToSubtract, @PathVariable ChronoUnit chronoUnit,
			Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit, amountToSubtract + " " + chronoUnit.name(),
				LocalDateTime.now().minus(amountToSubtract, chronoUnit), LocalDateTime.now(), true, true, model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/{amountToSubtract1}-{chronoUnit1}/{amountToSubtract2}-{chronoUnit2}", method = RequestMethod.GET)
	private String getGasSensingUpdateChartRange(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, @PathVariable int amountToSubtract1, @PathVariable ChronoUnit chronoUnit1,
			@PathVariable int amountToSubtract2, @PathVariable ChronoUnit chronoUnit2, Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit,
				amountToSubtract1 + " " + chronoUnit1.name() + " to " + amountToSubtract2 + " " + chronoUnit2.name(),
				LocalDateTime.now().minus(amountToSubtract1, chronoUnit1),
				LocalDateTime.now().minus(amountToSubtract2, chronoUnit2), false, false, model);
	}

	@RequestMapping(value = "/simplechart/{sensorName}/{description}/{amountToSubtract1}-{chronoUnit1}/{amountToSubtract2}-{chronoUnit2}", method = RequestMethod.GET)
	private String getGasSensingUpdateSimpleChartRange(@PathVariable String sensorName,
			@PathVariable String description, @RequestParam String unit, @PathVariable int amountToSubtract1,
			@PathVariable ChronoUnit chronoUnit1, @PathVariable int amountToSubtract2,
			@PathVariable ChronoUnit chronoUnit2, Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit,
				amountToSubtract1 + " " + chronoUnit1.name() + " to " + amountToSubtract2 + " " + chronoUnit2.name(),
				LocalDateTime.now().minus(amountToSubtract1, chronoUnit1),
				LocalDateTime.now().minus(amountToSubtract2, chronoUnit2), true, false, model);
	}

	private String getGasSensingUpdateChart(String sensorName, String description, String unit, String period,
			LocalDateTime beginning, LocalDateTime end, boolean simple, boolean streaming, Model model) {
		model.addAttribute("sensorName", sensorName);
		model.addAttribute("description", description);
		model.addAttribute("unit", unit);
		model.addAttribute("period", period);
		model.addAttribute("beginning", beginning.toString());
		model.addAttribute("end", end.toString());
		model.addAttribute("simple", simple);
		model.addAttribute("streaming", streaming);
		return "gassensor";
	}
}
