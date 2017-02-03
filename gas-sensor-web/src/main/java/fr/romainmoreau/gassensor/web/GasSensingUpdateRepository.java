package fr.romainmoreau.gassensor.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GasSensingUpdateRepository extends JpaRepository<GasSensingUpdate, Long> {
	GasSensingUpdate findFirstBySensorNameAndDescriptionAndUnitOrderByIdDesc(String sensorName, String description,
			String unit);

	List<GasSensingUpdate> findBySensorNameAndDescriptionAndLocalDateTimeBetweenOrderByIdDesc(String sensorName,
			String description, LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime);
}
