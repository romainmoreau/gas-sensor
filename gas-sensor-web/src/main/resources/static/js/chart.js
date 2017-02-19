function intervalsUpdatesToDatasets(intervals, updates) {
	var datasets = [];
	if (intervals.length == 0) {
		intervals.push({
			category : 'NONE'
		});
	}
	for (var i = 0; i < intervals.length; i++) {
		var interval = intervals[i];
		var data = [];
		for (var j = 0; j < updates.length; j++) {
			var u = updates[j];
			if ((interval.minValue == null || interval.minValue >= u.value)
					&& (interval.maxValue == null || interval.maxValue < u.value)) {
				data.push({
					x : u.localDateTime,
					y : u.value
				});
			}
		}
		var color;
		switch (interval.category) {
		case 'FINE':
			color = 'green';
			break;
		case 'WARNING':
			color = 'orange';
			break;
		case 'SEVERE':
			color = 'red';
			break;
		default:
			color = 'black';
			break;
		}
		datasets.push({
			data : data,
			pointBorderWidth : 0,
			pointBackgroundColor : color,
		});
	}
	return datasets;
}

function initChartFromResponse(intervals, updates) {
	var datasets = intervalsUpdatesToDatasets(intervals, updates);
	var canvas = $("canvas#chart");
	var chart = new Chart(canvas, {
		type : 'line',
		data : {
			datasets : datasets
		},
		options : {
			showLines : false,
			legend : {
				display : false
			},
			scales : {
				xAxes : [ {
					type : 'time',
				} ]
			},
			hover : {
				animationDuration : 0
			},
			animation : {
				duration : 0
			}
		}
	});
}

function fetchUpdates(intervals) {
	$.getJSON("/updates/" + sensorName + "/" + description + "/" + beginning
			+ "/" + end + "?unit=" + encodeURI(unit), function(updates) {
		initChartFromResponse(intervals, updates);
	});
}

function fetchIntervals() {
	$.getJSON("/intervals/" + description + "/?unit=" + encodeURI(unit),
			fetchUpdates);
}

$(document).ready(fetchIntervals);