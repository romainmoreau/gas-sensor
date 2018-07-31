package fr.romainmoreau.gassensor.web.jserialcomm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jserialcomm")
@Component
@ConfigurationProperties(prefix = "mhz19")
public class MhZ19JSerialCommProperties extends JSerialCommProperties {
}
