package fr.romainmoreau.gazsensor.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GazSensingUpdateRepository extends JpaRepository<GazSensingUpdate, Long> {
	GazSensingUpdate findFirstBySensorNameAndDescriptionAndUnitOrderByIdDesc(String sensorName, String description,
			String unit);

	List<GazSensingUpdate> findBySensorNameAndDescriptionAndLocalDateTimeBetweenOrderByIdDesc(String sensorName,
			String description, LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime);
}
