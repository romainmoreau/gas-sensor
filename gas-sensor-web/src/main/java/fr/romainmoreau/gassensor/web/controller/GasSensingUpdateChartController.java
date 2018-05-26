package fr.romainmoreau.gassensor.web.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.romainmoreau.gassensor.web.data.GasSensingUpdateRepository;

@Controller
public class GasSensingUpdateChartController {
	@Autowired
	private GasSensingUpdateRepository gasSensingUpdateRepository;

	@RequestMapping(value = "/charts", method = RequestMethod.GET)
	private String getGasSensors(Model model) {
		model.addAttribute("sensorNameDescriptionUnitMap", gasSensingUpdateRepository
				.findDistinctSensorNameDescriptionUnit().stream().collect(Collectors.groupingBy(e -> (String) e[0])));
		return "gassensors";
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/minute", method = RequestMethod.GET)
	private String getGasSensingUpdateChartMinute(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit, "Minute", LocalDateTime.now().minusMinutes(1),
				LocalDateTime.now(), model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/{n}-minutes", method = RequestMethod.GET)
	private String getGasSensingUpdateChartNMinutes(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, @PathVariable int n, Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit, n + " minutes",
				LocalDateTime.now().minusMinutes(n), LocalDateTime.now(), model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/hour", method = RequestMethod.GET)
	private String getGasSensingUpdateChartHour(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit, "Hour", LocalDateTime.now().minusHours(1),
				LocalDateTime.now(), model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/{n}-hours", method = RequestMethod.GET)
	private String getGasSensingUpdateChartNHours(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, @PathVariable int n, Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit, n + " hours", LocalDateTime.now().minusHours(n),
				LocalDateTime.now(), model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/day", method = RequestMethod.GET)
	private String getGasSensingUpdateChartDay(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit, "Day", LocalDateTime.now().minusDays(1),
				LocalDateTime.now(), model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/{n}-days", method = RequestMethod.GET)
	private String getGasSensingUpdateChartNDays(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit, @PathVariable int n, Model model) {
		return getGasSensingUpdateChart(sensorName, description, unit, n + " days", LocalDateTime.now().minusDays(n),
				LocalDateTime.now(), model);
	}

	private String getGasSensingUpdateChart(String sensorName, String description, String unit, String period,
			LocalDateTime beginning, LocalDateTime end, Model model) {
		model.addAttribute("sensorName", sensorName);
		model.addAttribute("description", description);
		model.addAttribute("unit", unit);
		model.addAttribute("period", period);
		model.addAttribute("beginning", beginning.toString());
		model.addAttribute("end", end.toString());
		return "gassensor";
	}
}
