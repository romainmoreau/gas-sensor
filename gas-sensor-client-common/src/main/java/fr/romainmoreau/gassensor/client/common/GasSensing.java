package fr.romainmoreau.gassensor.client.common;

import java.math.BigDecimal;

public class GasSensing {
	private final String description;

	private final BigDecimal value;

	private final String unit;

	public GasSensing(String description, BigDecimal value, String unit) {
		this.description = description;
		this.value = value;
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getUnit() {
		return unit;
	}

	@Override
	public String toString() {
		return description + ": " + value + " " + unit;
	}
}
