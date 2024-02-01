package fr.romainmoreau.gassensor.web.report;

public class GasSensingReport {
	private String sensorName;

	private String description;

	private String unit;

	private Double finePercent;

	private Double warningPercent;

	private Double severePercent;

	private Long longestMillisWithoutUpdate;

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

	public Double getFinePercent() {
		return finePercent;
	}

	public void setFinePercent(Double finePercent) {
		this.finePercent = finePercent;
	}

	public Double getWarningPercent() {
		return warningPercent;
	}

	public void setWarningPercent(Double warningPercent) {
		this.warningPercent = warningPercent;
	}

	public Double getSeverePercent() {
		return severePercent;
	}

	public void setSeverePercent(Double severePercent) {
		this.severePercent = severePercent;
	}

	public Long getLongestMillisWithoutUpdate() {
		return longestMillisWithoutUpdate;
	}

	public void setLongestMillisWithoutUpdate(Long longestMillisWithoutUpdate) {
		this.longestMillisWithoutUpdate = longestMillisWithoutUpdate;
	}
}
