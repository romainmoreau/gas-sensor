package fr.romainmoreau.gassensor.web.jserialcomm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jserialcomm")
@Component
@ConfigurationProperties(prefix = "ze08")
public class Ze08JSerialCommProperties extends JSerialCommProperties {
}
