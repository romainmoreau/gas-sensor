package fr.romainmoreau.gassensor.web.sms;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.romainmoreau.gassensor.datamodel.GasSensingUpdate;
import fr.romainmoreau.gassensor.web.data.GasSensingUpdateRepository;
import fr.romainmoreau.gsmmodem.client.api.GsmModemClient;
import fr.romainmoreau.gsmmodem.client.api.GsmModemException;

@RestController
@Profile("sms")
public class StatusController {
	@Autowired
	private GasSensingUpdateRepository gasSensingUpdateRepository;

	@Autowired
	private GsmModemClient gsmModemClient;

	@PostMapping("/status/sms")
	public void postStatusSms(@RequestParam String gsmNumber, @RequestParam String sms)
			throws IOException, GsmModemException {
		gasSensingUpdateRepository.findLatestUpdates().stream().map(this::gasSensingUpdateToSms)
				.forEach(responseSms -> {
					try {
						gsmModemClient.sendSms(gsmNumber, responseSms);
					} catch (IOException | GsmModemException e) {
						throw new RuntimeException(e);
					}
				});
	}

	private String gasSensingUpdateToSms(GasSensingUpdate gasSensingUpdate) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(gasSensingUpdate.getSensorName());
		stringBuilder.append(" (");
		stringBuilder.append(gasSensingUpdate.getDescription());
		stringBuilder.append("): ");
		stringBuilder.append(gasSensingUpdate.getValue().stripTrailingZeros().toPlainString());
		stringBuilder.append(gasSensingUpdate.getUnit());
		stringBuilder.append(" at ");
		stringBuilder.append(gasSensingUpdate.getLocalDateTime());
		return stringBuilder.toString();
	}
}
