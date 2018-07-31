package fr.romainmoreau.gassensor.web.jserialcomm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jserialcomm")
@Component
@ConfigurationProperties(prefix = "ze07")
public class Ze07JSerialCommProperties extends JSerialCommProperties {
}
