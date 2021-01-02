package fr.romainmoreau.gassensor.web.data;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.romainmoreau.gassensor.datamodel.GasSensingAlert;

public interface GasSensingAlertRepository extends JpaRepository<GasSensingAlert, Long> {
	GasSensingAlert findBySensorNameAndDescriptionAndUnit(String sensorName, String description, String unit);

}
