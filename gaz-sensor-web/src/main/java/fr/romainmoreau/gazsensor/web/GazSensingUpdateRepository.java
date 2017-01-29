package fr.romainmoreau.gazsensor.web;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GazSensingUpdateRepository extends JpaRepository<GazSensingUpdate, Long> {
	GazSensingUpdate findFirstBySensorNameAndDescriptionAndUnitOrderByIdDesc(String sensorName, String description,
			String unit);
}
