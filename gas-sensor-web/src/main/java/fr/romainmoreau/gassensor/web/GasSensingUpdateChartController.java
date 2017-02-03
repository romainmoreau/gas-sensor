package fr.romainmoreau.gassensor.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GasSensingUpdateChartController {
	@RequestMapping(value = "/chart/{sensorName}/{description}/hour", method = RequestMethod.GET)
	private String getGasSensingUpdateChartHour(@PathVariable String sensorName, @PathVariable String description,
			Model model) {
		return getGasSensingUpdateChart(sensorName, description, "Hour", LocalDateTime.now().minusHours(1),
				LocalDateTime.now(), model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/{n}-hours", method = RequestMethod.GET)
	private String getGasSensingUpdateChartNHours(@PathVariable String sensorName, @PathVariable String description,
			@PathVariable int n, Model model) {
		return getGasSensingUpdateChart(sensorName, description, n + " hours", LocalDateTime.now().minusHours(n),
				LocalDateTime.now(), model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/day", method = RequestMethod.GET)
	private String getGasSensingUpdateChartDay(@PathVariable String sensorName, @PathVariable String description,
			Model model) {
		return getGasSensingUpdateChart(sensorName, description, "Day", LocalDateTime.now().minusDays(1),
				LocalDateTime.now(), model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/{n}-days", method = RequestMethod.GET)
	private String getGasSensingUpdateChartNDays(@PathVariable String sensorName, @PathVariable String description,
			@PathVariable int n, Model model) {
		return getGasSensingUpdateChart(sensorName, description, n + " days", LocalDateTime.now().minusDays(n),
				LocalDateTime.now(), model);
	}

	private String getGasSensingUpdateChart(String sensorName, String description, String period,
			LocalDateTime beginning, LocalDateTime end, Model model) {
		model.addAttribute("sensorName", sensorName);
		model.addAttribute("description", description);
		model.addAttribute("period", period);
		model.addAttribute("beginning", beginning.toString());
		model.addAttribute("end", end.toString());
		return "gassensor";
	}
}
