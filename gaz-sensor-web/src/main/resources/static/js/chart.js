function responseToData(response) {
	var data = [];
	for (var i = 0; i < response.length; i++) {
		var u = response[i];
		data.push({
			x : u.localDateTime,
			y : u.value
		});
	}
	return data;
}

function initChartFromResponse(response) {
	var data = responseToData(response);
	var canvas = $("canvas#chart");
	var chart = new Chart(canvas, {
		type : 'line',
		data : {
			datasets : [ {
				data : data
			} ]
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

function initChart() {
	$.getJSON("/updates/" + sensorName + "/" + description + "/" + beginning
			+ "/" + end, initChartFromResponse);
}

$(document).ready(initChart);