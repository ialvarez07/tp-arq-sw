var dataPoints = [];
var measurements = [];
var chart;
var timeout = 10;
var strGraph = "";
var maxTemp = 50;

$.getScript("/js/canvasjs.min.js", function () {
});

function showGraph() {
    var e = document.getElementById("ddlViewBy");
    strGraph = e.options[e.selectedIndex].value;
    dataPoints = [];
    measurements = [];
    maxTemp = document.getElementById("maxTempInput").value;
    console.log(maxTemp);
    main();
}

function main() {

    $.getJSON("http://localhost:8080/measurement/measurements", function (data) {

        $.each(data, function (key, value) {
            if (value.devName === strGraph) {
                measurements.push(value);
                dataPoints.push({x: value.dateTime*1000, y: value.temp});
            }
        });

        chart = new CanvasJS.Chart("chartContainer", {
            title: {
                text: "Live Chart con temperaturas a lo largo el tiempo"
            },
            data: [{
                    type: "line",
                    xValueType: "dateTime",
                    dataPoints: dataPoints
                }]
        });

        chart.render();
        updateChart();
    });
};

function updateChart() {
    $.getJSON("http://localhost:8080/measurement/deltameasurements?devName=" + strGraph + "&dateTime=" + measurements[measurements.length - 1].dateTime,
        function (data) {
            $.each(data, function (key, value) {
                measurements.push(value);
                dataPoints.push({x: value.dateTime*1000, y: value.temp});
                    if (value.temp >= maxTemp) {
                        var date = new Date(value.dateTime*1000);
                        setTimeout(function() {alert('La temperatura de ' + date + ' es demasiado elevada (mayor a ' + maxTemp + ')');},1);
                    }
            });
            
            while (dataPoints.length >  20 )
            {
                    dataPoints.shift();	
                    measurements.shift();
            }
            
            chart.render();
            setTimeout(function() {updateChart()}, timeout * 1000);
    });
}