package fr.romainmoreau.gassensor.web.jserialcomm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Profile("jserialcomm")
@ConfigurationProperties(prefix = "mhz19")
public class MhZ19JSerialCommProperties extends JSerialCommProperties {
}
