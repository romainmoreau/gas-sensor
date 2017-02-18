package fr.romainmoreau.gassensor.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jssc")
@Component
@ConfigurationProperties(prefix = "mhz19")
public class MhZ19JsscProperties extends JsscProperties {
}
