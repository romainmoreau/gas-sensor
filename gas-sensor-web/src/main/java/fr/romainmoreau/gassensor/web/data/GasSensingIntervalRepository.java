package fr.romainmoreau.gassensor.web.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.romainmoreau.gassensor.datamodel.GasSensingInterval;

public interface GasSensingIntervalRepository extends JpaRepository<GasSensingInterval, Long> {
	List<GasSensingInterval> findByDescriptionAndUnit(String description, String unit);
}
