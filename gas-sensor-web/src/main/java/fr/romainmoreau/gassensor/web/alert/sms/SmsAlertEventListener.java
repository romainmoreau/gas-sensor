package fr.romainmoreau.gassensor.web.alert.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.web.alert.AlertApplicationEvent;
import fr.romainmoreau.gsmmodem.client.api.GsmModemClient;

@Component
@Profile("alert-sms")
public class SmsAlertEventListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(SmsAlertEventListener.class);

	@Autowired
	private GsmModemClient gsmModemClient;

	@Autowired
	private SmsAlertProperties smsAlertProperties;

	@Async
	@EventListener(AlertApplicationEvent.class)
	public void onAlertApplicationEvent(AlertApplicationEvent alertApplicationEvent) {
		try {
			gsmModemClient.sendSms(smsAlertProperties.getGsmNumber(), alertApplicationEvent.toShortMessage());
		} catch (Exception e) {
			LOGGER.error("Exception while sending SMS alert", e);
		}
	}
}
