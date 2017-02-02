package fr.romainmoreau.gazsensor.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GazSensingUpdateChartController {
	@RequestMapping(value = "/chart/{sensorName}/{description}/hour", method = RequestMethod.GET)
	private String getGazSensingUpdateChartHour(@PathVariable String sensorName, @PathVariable String description,
			Model model) {
		return getGazSensingUpdateChart(sensorName, description, LocalDateTime.now().minusHours(1), LocalDateTime.now(),
				model);
	}

	@RequestMapping(value = "/chart/{sensorName}/{description}/day", method = RequestMethod.GET)
	private String getGazSensingUpdateChartDay(@PathVariable String sensorName, @PathVariable String description,
			Model model) {
		return getGazSensingUpdateChart(sensorName, description, LocalDateTime.now().minusDays(1), LocalDateTime.now(),
				model);
	}

	private String getGazSensingUpdateChart(@PathVariable String sensorName, @PathVariable String description,
			@PathVariable LocalDateTime beginning, @PathVariable LocalDateTime end, Model model) {
		model.addAttribute("sensorName", sensorName);
		model.addAttribute("description", description);
		model.addAttribute("beginning", beginning.toString());
		model.addAttribute("end", end.toString());
		return "gazsensor";
	}
}
