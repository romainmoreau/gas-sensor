package fr.romainmoreau.gassensor.web.report;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.romainmoreau.gassensor.datamodel.GasSensingIntervalCategory;
import fr.romainmoreau.gassensor.datamodel.GasSensingUpdatesRange;
import fr.romainmoreau.gassensor.web.common.GasSensingIntervalService;
import fr.romainmoreau.gassensor.web.data.GasSensingUpdateRepository;
import fr.romainmoreau.gassensor.web.data.GasSensingUpdatesRangeRepository;

@Component
@Profile("report")
public class ReportScheduler {
	@Autowired
	private GasSensingUpdatesRangeRepository gasSensingUpdatesRangeRepository;

	@Autowired
	private GasSensingUpdateRepository gasSensingUpdateRepository;

	@Autowired
	private GasSensingIntervalService gasSensingIntervalService;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Scheduled(cron = "0 0 12 * * *")
	private void sendDailyReport() {
		var today = LocalDate.now();
		var todayStartOfDay = today.atStartOfDay();
		var yesterday = today.minusDays(1);
		var yesterdayStartOfDay = today.minusDays(1).atStartOfDay();
		applicationEventPublisher.publishEvent(new ReportApplicationEvent(
				gasSensingUpdatesRangeRepository.findAll().stream()
						.map(range -> getGasSensingReport(todayStartOfDay, yesterdayStartOfDay, range)).toList(),
				yesterday));
	}

	private GasSensingReport getGasSensingReport(LocalDateTime todayStartOfDay, LocalDateTime yesterdayStartOfDay,
			GasSensingUpdatesRange gasSensingUpdatesRange) {
		var yesterdayUpdates = gasSensingUpdateRepository
				.findBySensorNameAndDescriptionAndUnitAndLocalDateTimeBetweenOrderByIdAsc(
						gasSensingUpdatesRange.getSensorName(), gasSensingUpdatesRange.getDescription(),
						gasSensingUpdatesRange.getUnit(), yesterdayStartOfDay, todayStartOfDay);
		var firstBeforeYesterdayUpdate = gasSensingUpdateRepository
				.findFirstBySensorNameAndDescriptionAndUnitAndLocalDateTimeLessThanOrderByIdDesc(
						gasSensingUpdatesRange.getSensorName(), gasSensingUpdatesRange.getDescription(),
						gasSensingUpdatesRange.getUnit(), yesterdayStartOfDay);
		var gasSensingReport = new GasSensingReport();
		gasSensingReport.setSensorName(gasSensingUpdatesRange.getSensorName());
		gasSensingReport.setDescription(gasSensingUpdatesRange.getDescription());
		gasSensingReport.setUnit(gasSensingUpdatesRange.getUnit());
		var lastUpdate = firstBeforeYesterdayUpdate;
		var categoryMillisMap = Stream.of(GasSensingIntervalCategory.values())
				.collect(Collectors.toMap(Function.identity(), c -> 0l));
		Long longestMillisWithoutUpdate = null;
		for (var yesterdayUpdate : yesterdayUpdates) {
			if (lastUpdate != null) {
				var category = gasSensingIntervalService.getCategory(lastUpdate);
				var millis = (lastUpdate == firstBeforeYesterdayUpdate ? yesterdayStartOfDay
						: lastUpdate.getLocalDateTime()).until(yesterdayUpdate.getLocalDateTime(), ChronoUnit.MILLIS);
				categoryMillisMap.put(category, categoryMillisMap.get(category) + millis);
				longestMillisWithoutUpdate = (lastUpdate == firstBeforeYesterdayUpdate ? millis
						: Math.max(longestMillisWithoutUpdate, millis));
			}
			lastUpdate = yesterdayUpdate;
		}
		if (!yesterdayUpdates.isEmpty()) {
			var category = gasSensingIntervalService.getCategory(lastUpdate);
			var millis = lastUpdate.getLocalDateTime().until(todayStartOfDay, ChronoUnit.MILLIS);
			categoryMillisMap.put(category, categoryMillisMap.get(category) + millis);
			longestMillisWithoutUpdate = Math.max(longestMillisWithoutUpdate, millis);
		}
		var millisSum = categoryMillisMap.values().stream().mapToLong(m -> m).sum();
		if (millisSum != 0l) {
			gasSensingReport
					.setFinePercent((double) categoryMillisMap.get(GasSensingIntervalCategory.FINE) / millisSum);
			gasSensingReport
					.setWarningPercent((double) categoryMillisMap.get(GasSensingIntervalCategory.WARNING) / millisSum);
			gasSensingReport
					.setSeverePercent((double) categoryMillisMap.get(GasSensingIntervalCategory.SEVERE) / millisSum);
		}
		gasSensingReport.setLongestMillisWithoutUpdate(longestMillisWithoutUpdate);
		return gasSensingReport;
	}
}
