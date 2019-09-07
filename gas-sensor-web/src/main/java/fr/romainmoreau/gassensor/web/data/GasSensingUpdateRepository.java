package fr.romainmoreau.gassensor.web.data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.romainmoreau.gassensor.datamodel.GasSensingUpdate;

public interface GasSensingUpdateRepository extends JpaRepository<GasSensingUpdate, Long> {
	GasSensingUpdate findFirstBySensorNameAndDescriptionAndUnitOrderByIdDesc(String sensorName, String description,
			String unit);

	List<GasSensingUpdate> findBySensorNameAndDescriptionAndUnitAndLocalDateTimeBetweenOrderByIdAsc(String sensorName,
			String description, String unit, LocalDateTime minLocalDateTime, LocalDateTime maxLocalDateTime);

	@Query("select sensorName,description,unit,min(localDateTime),max(localDateTime) from GasSensingUpdate u group by sensorName,description,unit")
	List<Object[]> findAllDistinctSensorNameDescriptionUnitWithRange();

	@Query("select u1 from GasSensingUpdate u1 where u1.id in (select max(u2.id) from GasSensingUpdate u2 group by u2.sensorName,u2.description,u2.unit)")
	List<GasSensingUpdate> findLatestUpdates();
}
