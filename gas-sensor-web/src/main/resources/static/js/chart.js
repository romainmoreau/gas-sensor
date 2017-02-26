var intervals, updates;

function intervalsUpdatesToDatasets() {
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

function updateChart() {
	var datasets = intervalsUpdatesToDatasets();
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

function init() {
	updateChart();
	var webSocket = new SockJS(stompEndpointUrl);
	var stompClient = Stomp.over(webSocket);
	stompClient.connect({}, function() {
		stompClient.subscribe('/updates/' + sensorName + '/' + description
				+ '/' + unit, function(update) {
			updates.push(JSON.parse(update.body));
			updateChart();
		});
	}, function(error) {
		alert(error);
	});
}

function fetchUpdates() {
	$.getJSON("/updates/" + sensorName + "/" + description + "/" + beginning
			+ "/" + end + "?unit=" + encodeURI(unit), function(response) {
		updates = response;
		init();
	});
}

function fetchIntervals() {
	$.getJSON("/intervals/" + description + "/?unit=" + encodeURI(unit),
			function(response) {
				intervals = response;
				fetchUpdates();
			});
}

$(document).ready(fetchIntervals);