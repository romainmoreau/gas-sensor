package fr.romainmoreau.gassensor.web.alert.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.web.alert.AlertApplicationEvent;

@Component
@Profile("alert-email")
public class EmailAlertEventListener {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailAlertProperties emailAlertProperties;

	@Async
	@EventListener(AlertApplicationEvent.class)
	public void onAlertApplicationEvent(AlertApplicationEvent alertApplicationEvent) {
		var simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(emailAlertProperties.getTo());
		simpleMailMessage.setSubject(alertApplicationEvent.toShortMessage());
		simpleMailMessage.setText("");
		javaMailSender.send(simpleMailMessage);
	}
}
