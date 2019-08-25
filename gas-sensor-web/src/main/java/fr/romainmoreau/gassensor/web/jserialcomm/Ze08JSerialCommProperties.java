package fr.romainmoreau.gassensor.web.jserialcomm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Profile("jserialcomm")
@ConfigurationProperties(prefix = "ze08")
public class Ze08JSerialCommProperties extends JSerialCommProperties {
}
