package fr.romainmoreau.gassensor.web;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

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
