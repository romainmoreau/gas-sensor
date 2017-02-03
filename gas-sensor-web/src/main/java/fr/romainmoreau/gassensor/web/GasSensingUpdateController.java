package fr.romainmoreau.gassensor.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GasSensingUpdateController {
	@Autowired
	private GasSensingUpdateRepository gasSensingUpdateRepository;

	@RequestMapping(path = "/updates/{sensorName}/{description}/{beginning}/{end}", method = RequestMethod.GET, produces = {
			"application/json" })
	public List<GasSensingUpdate> getUpdates(@PathVariable String sensorName, @PathVariable String description,
			@PathVariable LocalDateTime beginning, @PathVariable LocalDateTime end) {
		return gasSensingUpdateRepository.findBySensorNameAndDescriptionAndLocalDateTimeBetweenOrderByIdDesc(sensorName,
				description, beginning, end);
	}
}
