package fr.romainmoreau.gassensor.web.jserialcomm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Profile("jserialcomm")
@ConfigurationProperties(prefix = "pmsa003")
public class Pmsa003JSerialCommProperties extends JSerialCommProperties {
}
