package fr.romainmoreau.gassensor.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GasSensingUpdateRepository extends JpaRepository<GasSensingUpdate, Long> {
	GasSensingUpdate findFirstBySensorNameAndDescriptionAndUnitOrderByIdDesc(String sensorName, String description,
			String unit);

	List<GasSensingUpdate> findBySensorNameAndDescriptionAndUnitAndLocalDateTimeBetweenOrderByIdDesc(String sensorName,
			String description, String unit, LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime);

	@Query("select distinct sensorName,description,unit from GasSensingUpdate u")
	List<Object[]> findDistinctSensorNameDescriptionUnit();
}
