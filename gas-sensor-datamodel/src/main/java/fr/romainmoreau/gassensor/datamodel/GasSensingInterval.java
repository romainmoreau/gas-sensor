package fr.romainmoreau.gassensor.datamodel;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(indexes = { @Index(columnList = "description,unit,category", unique = false) })
public class GasSensingInterval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String unit;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private GasSensingIntervalCategory category;

	@Column(nullable = true, precision = 10, scale = 5)
	private BigDecimal minValue;

	@Column(nullable = true, precision = 10, scale = 5)
	private BigDecimal maxValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public GasSensingIntervalCategory getCategory() {
		return category;
	}

	public void setCategory(GasSensingIntervalCategory category) {
		this.category = category;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}
}
