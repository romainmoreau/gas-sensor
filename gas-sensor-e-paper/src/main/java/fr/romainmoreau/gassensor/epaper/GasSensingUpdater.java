package fr.romainmoreau.gassensor.epaper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.epaper.epaper.EPaperService;
import fr.romainmoreau.gassensor.epaper.gassensing.GasSensingUpdateService;
import fr.romainmoreau.gassensor.epaper.gassensing.LastGasSensingUpdates;

@Component
public class GasSensingUpdater {
	@Autowired
	private GasSensingUpdateService gasSensingUpdateService;

	@Autowired
	private EPaperService ePaperService;

	@Scheduled(fixedDelay = 10000)
	private void update() {
		LastGasSensingUpdates lastGasSensingUpdates = gasSensingUpdateService.getLastGasSensingUpdates();
		ePaperService.update(lastGasSensingUpdates);
	}
}
