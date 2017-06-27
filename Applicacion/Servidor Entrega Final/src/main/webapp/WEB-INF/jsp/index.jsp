<%-- 
    Document   : holamundo
    Created on : 22/06/2017, 10:14:05
    Author     : Facundo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grafica con Temperaturas</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/canvasjs/1.7.0/canvasjs.js"></script>
        <script src="/js/scriptGraph.js"></script>

    </head>
    <body>
        <h1>IoT - Medicion de temperatura</h1>
        <h2>Alvarez - Parra - Vecchiet</h2>
        <div id="chartContainer" style="height: 300px; width: 100%">

        </div>
        
        <select id="ddlViewBy">
            <option value="Dispositivo 1" selected="selected">Dispositivo 1</option>
            <option value="Dev 1">Dev 1</option>
            <option value="Caldera">Caldera</option>
            <option value="Motor Mezcladora">Motor Mezcladora</option>
        </select>
        
         <input placeholder="Temperatura Máx." id="maxTempInput" type="text" maxlength="20"/>
         <button onclick="showGraph()">Mostrar Temperaturas</button>
    </body>
</html>
