package fr.romainmoreau.gassensor.web.jserialcomm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Profile("jserialcomm")
@ConfigurationProperties(prefix = "zph01")
public class Zph01JSerialCommProperties extends JSerialCommProperties {
}
