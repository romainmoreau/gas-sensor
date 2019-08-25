package fr.romainmoreau.gassensor.web.jserialcomm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Profile("jserialcomm")
@ConfigurationProperties(prefix = "zh03a")
public class Zh03AJSerialCommProperties extends JSerialCommProperties {
}
