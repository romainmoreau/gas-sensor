package fr.romainmoreau.gassensor.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.romainmoreau.gassensor.datamodel.GasSensingAlert;
import fr.romainmoreau.gassensor.web.data.GasSensingAlertRepository;

@RestController
@RequestMapping("/alert")
public class GasSensingAlertController {
	@Autowired
	private GasSensingAlertRepository gasSensingAlertRepository;

	@PostMapping()
	public GasSensingAlert postAlert(@RequestBody GasSensingAlert gasSensingAlert) {
		return gasSensingAlertRepository.save(gasSensingAlert);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAlert(@PathVariable long id) {
		gasSensingAlertRepository.deleteById(id);
	}

	@GetMapping("/{sensorName}/{description}")
	public GasSensingAlert getAlert(@PathVariable String sensorName, @PathVariable String description,
			@RequestParam String unit) {
		return gasSensingAlertRepository.findBySensorNameAndDescriptionAndUnit(sensorName, description, unit);
	}
}
