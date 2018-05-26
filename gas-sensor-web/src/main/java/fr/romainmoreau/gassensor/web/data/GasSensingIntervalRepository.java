package fr.romainmoreau.gassensor.web.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GasSensingIntervalRepository extends JpaRepository<GasSensingInterval, Long> {
	List<GasSensingInterval> findByDescriptionAndUnit(String description, String unit);
}
