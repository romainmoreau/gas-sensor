package fr.romainmoreau.gassensor.datamodel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = { @Index(columnList = "sensorName,description,unit", unique = true) })
public class GasSensingUpdatesRange {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String sensorName;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String unit;

	@Column(nullable = false)
	private LocalDateTime minLocalDateTime;

	@Column(nullable = false)
	private LocalDateTime maxLocalDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
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

	public LocalDateTime getMinLocalDateTime() {
		return minLocalDateTime;
	}

	public void setMinLocalDateTime(LocalDateTime minLocalDateTime) {
		this.minLocalDateTime = minLocalDateTime;
	}

	public LocalDateTime getMaxLocalDateTime() {
		return maxLocalDateTime;
	}

	public void setMaxLocalDateTime(LocalDateTime maxLocalDateTime) {
		this.maxLocalDateTime = maxLocalDateTime;
	}
}
