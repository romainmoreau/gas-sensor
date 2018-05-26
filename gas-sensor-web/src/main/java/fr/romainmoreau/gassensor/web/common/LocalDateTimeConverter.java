package fr.romainmoreau.gassensor.web.common;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
	@Override
	public LocalDateTime convert(String source) {
		return LocalDateTime.parse(source);
	}
}
