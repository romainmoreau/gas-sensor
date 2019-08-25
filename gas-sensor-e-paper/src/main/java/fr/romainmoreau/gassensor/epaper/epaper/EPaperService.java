package fr.romainmoreau.gassensor.epaper.epaper;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdurmont.elapsedtime.ElapsedTime;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.DrawingColors;
import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.api.HorizontalAlignment;
import fr.romainmoreau.epaper.client.api.VerticalAlignment;
import fr.romainmoreau.epaper.client.api.table.Border;
import fr.romainmoreau.epaper.client.api.table.Cell;
import fr.romainmoreau.epaper.client.api.table.Column;
import fr.romainmoreau.epaper.client.api.table.PaddedCellContent;
import fr.romainmoreau.epaper.client.api.table.Row;
import fr.romainmoreau.epaper.client.api.table.Table;
import fr.romainmoreau.epaper.client.api.table.TextCellContent;
import fr.romainmoreau.gassensor.datamodel.GasSensingUpdate;
import fr.romainmoreau.gassensor.epaper.gassensing.LastGasSensingUpdates;

@Service
public class EPaperService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EPaperService.class);

	@Autowired
	private EPaperClient ePaperClient;

	public void update(LastGasSensingUpdates lastGasSensingUpdates) {
		try {
			ePaperClient.setDrawingColors(new DrawingColors(Color.WHITE, Color.WHITE));
			ePaperClient.clear();
			Map<String, List<GasSensingUpdate>> descriptionLastGasSensingUpdateListMap = lastGasSensingUpdates
					.getLastGasSensingUpdateList().stream()
					.collect(Collectors.groupingBy(GasSensingUpdate::getDescription));
			int rows = lastGasSensingUpdates.getLastGasSensingUpdateList().size()
					+ descriptionLastGasSensingUpdateListMap.values().stream().mapToInt(List::size).map(s -> s - 1)
							.sum()
					+ 1;
			Table table = new Table();
			table.getVerticalBorders().add(new Border(0, null));
			table.getColumns().add(new Column(0.3));
			table.getVerticalBorders().add(new Border(0, null));
			table.getColumns().add(new Column(0.2));
			table.getVerticalBorders().add(new Border(0, null));
			table.getColumns().add(new Column(0.2));
			table.getVerticalBorders().add(new Border(0, null));
			table.getColumns().add(new Column(0.3));
			table.getVerticalBorders().add(new Border(0, null));
			table.getHorizontalBorders().add(new Border(0, null));
			for (int i = 0; i < rows; i++) {
				table.getRows().add(new Row(1d / rows));
				table.getHorizontalBorders().add(new Border(0, null));
			}
			int i = 0;
			for (Entry<String, List<GasSensingUpdate>> descriptionLastGasSensingUpdateListEntry : descriptionLastGasSensingUpdateListMap
					.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
					.collect(Collectors.toList())) {
				String description = descriptionLastGasSensingUpdateListEntry.getKey();
				List<GasSensingUpdate> lastGasSensingUpdateList = descriptionLastGasSensingUpdateListEntry.getValue();
				if (lastGasSensingUpdateList.size() > 1) {
					table.getCells()
							.add(new Cell(0, i, 0, Color.WHITE,
									new PaddedCellContent(0, 0, 0, 0,
											new TextCellContent(Color.BLACK, Color.WHITE, FontSize.DOTS_32, 0,
													HorizontalAlignment.RIGHT, VerticalAlignment.CENTER,
													description))));
					i++;
				}
				for (GasSensingUpdate gasSensingUpdate : lastGasSensingUpdateList.stream()
						.sorted((u1, u2) -> u1.getSensorName().compareTo(u2.getSensorName()))
						.collect(Collectors.toList())) {
					table.getCells()
							.add(new Cell(0, i, 0, Color.WHITE,
									new PaddedCellContent(0, 0, 0, 0,
											new TextCellContent(Color.BLACK, Color.WHITE, FontSize.DOTS_32, 0,
													HorizontalAlignment.RIGHT, VerticalAlignment.CENTER,
													lastGasSensingUpdateList.size() > 1
															? gasSensingUpdate.getSensorName()
															: gasSensingUpdate.getDescription()))));
					table.getCells()
							.add(new Cell(1, i, 0, Color.WHITE,
									new TextCellContent(Color.BLACK, Color.WHITE, FontSize.DOTS_32, 0,
											HorizontalAlignment.RIGHT, VerticalAlignment.CENTER,
											gasSensingUpdate.getValue().stripTrailingZeros().toPlainString())));
					table.getCells()
							.add(new Cell(2, i, 0, Color.WHITE,
									new PaddedCellContent(10, 0, 0, 0,
											new TextCellContent(Color.BLACK, Color.WHITE, FontSize.DOTS_32, 0,
													HorizontalAlignment.LEFT, VerticalAlignment.CENTER,
													gasSensingUpdate.getUnit().replace('°', 'o')))));
					table.getCells()
							.add(new Cell(3, i, 0, Color.WHITE, new TextCellContent(Color.BLACK, Color.WHITE,
									FontSize.DOTS_32, 0, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER,
									ElapsedTime.getFromDurationMillis(gasSensingUpdate.getLocalDateTime()
											.until(lastGasSensingUpdates.getLocalDateTime(), ChronoUnit.MILLIS)))));
					i++;
				}
			}
			table.getCells()
					.add(new Cell(0, i, 0, Color.WHITE,
							new PaddedCellContent(0, 0, 0, 0,
									new TextCellContent(Color.BLACK, Color.WHITE, FontSize.DOTS_32, 0,
											HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, "Update date time"))));
			table.getCells()
					.add(new Cell(2, i, 0, Color.WHITE,
							new TextCellContent(Color.BLACK, Color.WHITE, FontSize.DOTS_32, 0, HorizontalAlignment.LEFT,
									VerticalAlignment.CENTER,
									lastGasSensingUpdates.getLocalDateTime().toLocalDate().toString())));
			table.getCells()
					.add(new Cell(3, i, 0, Color.WHITE,
							new TextCellContent(Color.BLACK, Color.WHITE, FontSize.DOTS_32, 0,
									HorizontalAlignment.RIGHT, VerticalAlignment.CENTER,
									lastGasSensingUpdates.getLocalDateTime().toLocalTime().toString())));
			i++;
			ePaperClient.drawTable(0, 0, EPaperClient.WIDTH - 1, EPaperClient.HEIGHT - 1, table);
			ePaperClient.refreshAndUpdate();
		} catch (IOException | EPaperException e) {
			LOGGER.error("Exception while updating e-paper", e);
		}
	}
}
