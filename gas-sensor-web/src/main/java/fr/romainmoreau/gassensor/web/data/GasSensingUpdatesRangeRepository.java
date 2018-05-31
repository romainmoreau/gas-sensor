package fr.romainmoreau.gassensor.web.data;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.romainmoreau.gassensor.datamodel.GasSensingUpdatesRange;

public interface GasSensingUpdatesRangeRepository extends JpaRepository<GasSensingUpdatesRange, Long> {
}
