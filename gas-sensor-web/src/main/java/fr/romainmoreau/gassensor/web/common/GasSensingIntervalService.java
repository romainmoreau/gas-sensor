package fr.romainmoreau.gassensor.web.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.romainmoreau.gassensor.datamodel.GasSensingInterval;
import fr.romainmoreau.gassensor.datamodel.GasSensingIntervalCategory;
import fr.romainmoreau.gassensor.datamodel.GasSensingUpdate;
import fr.romainmoreau.gassensor.web.data.GasSensingIntervalRepository;

@Service
public class GasSensingIntervalService {
    @Autowired
    private GasSensingIntervalRepository gasSensingIntervalRepository;

    public GasSensingIntervalCategory getCategory(GasSensingUpdate gasSensingUpdate) {
        var gasSensingIntervalList = gasSensingIntervalRepository
                .findByDescriptionAndUnit(gasSensingUpdate.getDescription(), gasSensingUpdate.getUnit());
        if (gasSensingIntervalList.isEmpty()) {
            return GasSensingIntervalCategory.FINE;
        }
        return gasSensingIntervalList.stream().filter(
                i -> (i.getMinValue() == null || gasSensingUpdate.getReadValue().compareTo(i.getMinValue()) >= 0)
                        && (i.getMaxValue() == null || gasSensingUpdate.getReadValue().compareTo(i.getMaxValue()) < 0))
                .map(GasSensingInterval::getCategory).findAny().orElse(GasSensingIntervalCategory.FINE);
    }
}
