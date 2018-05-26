package fr.romainmoreau.gassensor.web.jssc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jssc")
@Component
@ConfigurationProperties(prefix = "zph01")
public class Zph01JsscProperties extends JsscProperties {
}
