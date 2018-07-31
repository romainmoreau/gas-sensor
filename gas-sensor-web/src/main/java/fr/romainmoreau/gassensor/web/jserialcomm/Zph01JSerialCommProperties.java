package fr.romainmoreau.gassensor.web.jserialcomm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jserialcomm")
@Component
@ConfigurationProperties(prefix = "zph01")
public class Zph01JSerialCommProperties extends JSerialCommProperties {
}
