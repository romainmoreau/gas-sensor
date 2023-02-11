package fr.romainmoreau.gassensor.web.alert;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.datamodel.GasSensingInterval;
import fr.romainmoreau.gassensor.datamodel.GasSensingIntervalCategory;
import fr.romainmoreau.gassensor.datamodel.GasSensingUpdate;
import fr.romainmoreau.gassensor.web.common.GasSensingUpdateApplicationEvent;
import fr.romainmoreau.gassensor.web.data.GasSensingAlertRepository;
import fr.romainmoreau.gassensor.web.data.GasSensingIntervalRepository;

@Component
@Profile("alert")
public class AlertGasSensingUpdateEventListener {
	@Autowired
	private GasSensingIntervalRepository gasSensingIntervalRepository;

	@Autowired
	private GasSensingAlertRepository gasSensingAlertRepository;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private AlertProperties alertProperties;

	private ConcurrentMap<Long, AlertApplicationEvent> alertIdLastAlertApplicationEventConcurrentMap;

	public AlertGasSensingUpdateEventListener() {
		alertIdLastAlertApplicationEventConcurrentMap = new ConcurrentHashMap<>();
	}

	@Scheduled(fixedDelay = 10000)
	private void checkForEventsToPublish() {
		var now = System.currentTimeMillis();
		var entriesToPublishAndRemove = alertIdLastAlertApplicationEventConcurrentMap.entrySet().stream()
				.filter(entry -> now - entry.getValue().getTimestamp() >= alertProperties.getDebounceMillis())
				.collect(Collectors.toList());
		entriesToPublishAndRemove.stream().map(Entry::getValue).forEach(applicationEventPublisher::publishEvent);
		entriesToPublishAndRemove.stream().forEach(
				entry -> alertIdLastAlertApplicationEventConcurrentMap.remove(entry.getKey(), entry.getValue()));
	}

	@EventListener(GasSensingUpdateApplicationEvent.class)
	public void onGasSensingUpdateApplicationEvent(GasSensingUpdateApplicationEvent gasSensingUpdateApplicationEvent) {
		var gasSensingUpdate = gasSensingUpdateApplicationEvent.getGasSensingUpdate();
		var gasSensingAlert = gasSensingAlertRepository.findBySensorNameAndDescriptionAndUnit(
				gasSensingUpdate.getSensorName(), gasSensingUpdate.getDescription(), gasSensingUpdate.getUnit());
		if (gasSensingAlert == null || gasSensingAlert.getThresholdCategory() == GasSensingIntervalCategory.FINE) {
			return;
		}
		var lastCategory = gasSensingUpdateApplicationEvent.getLastGasSensingUpdate() != null
				? getCategory(gasSensingUpdateApplicationEvent.getLastGasSensingUpdate())
				: null;
		var category = getCategory(gasSensingUpdate);
		var alertOn = isAlertOn(category, gasSensingAlert.getThresholdCategory());
		if (lastCategory == null || alertOn == isAlertOn(lastCategory, gasSensingAlert.getThresholdCategory())) {
			return;
		}
		var alertApplicationEvent = new AlertApplicationEvent(gasSensingAlert, category, lastCategory, alertOn);
		var lastAlertApplicationEvent = alertIdLastAlertApplicationEventConcurrentMap.get(gasSensingAlert.getId());
		alertIdLastAlertApplicationEventConcurrentMap.put(gasSensingAlert.getId(), alertApplicationEvent);
		if (lastAlertApplicationEvent != null && alertApplicationEvent.getTimestamp()
				- lastAlertApplicationEvent.getTimestamp() < alertProperties.getDebounceMillis()) {
			return;
		}
		applicationEventPublisher.publishEvent(alertApplicationEvent);
	}

	private GasSensingIntervalCategory getCategory(GasSensingUpdate gasSensingUpdate) {
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

	private boolean isAlertOn(GasSensingIntervalCategory category, GasSensingIntervalCategory thresholdCategory) {
		return category.ordinal() >= thresholdCategory.ordinal();
	}
}
