<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value="/resources/css/styles.css"/>">
<script src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.tablesorter.min.js" />"></script>
<script src="<c:url value="/resources/js/Chart.min.js" />"></script>
</head>
<body>
	<div id="wrapper">
		<input id="toggle" type="button" value="Comparar ${mode}" onclick="toggle();">
		<c:if test="${not empty nombres && not empty fechas && not empty puntos && not empty colores}">
			<canvas id="myChart" width="400" height="400"></canvas>
		</c:if>
	</div>
	<script type="text/javascript">
		function toggle() {
			$('#toggle').prop('disabled', true);
			var actualMode = $('#toggle').attr("value");
			var url = window.location.href;
			if (url.includes("Fragmentos")) {
				url = url.replace("Fragmentos", "Puntos");
				actualMode = "Comparar Puntos";
			}
			else {
				url = url.replace("Puntos", "Fragmentos");
				actualMode = "Comparar Fragmentos";
			}
			console.log(actualMode);
			console.log(url);
			$('#toggle').attr("value", actualMode);
			window.location.href = url;
		}

		var nombres = ${nombres};
		var fechas = ${fechas};
		var puntos = ${puntos};
		var colores = ${colores}
		var ctx = $("#myChart");
		var datasets = [];
		var i = 0;
		nombres.forEach(function(nombre) {
			datasets.push({data : puntos[i], borderColor : getRGBa(colores[i], 1), backgroundColor : getRGBa(colores[i], "0.0" + i), label : nombre, borderWidth : 1});
			i++;
		});

		var data = {
				datasets : datasets,
				labels : fechas
			};
		
		var myChart = new Chart(ctx,
				{
					type : 'line',
					data : data,
					options : {
						tooltipFillColor : "rgba(0,160,0,0.8)",
						animationEasing : "easeOutBounce",
					}
				});
		function getRGBa(hex, opacity) {
			var color = "rgba(";
			color += parseInt(hex.substring(1, 3), 16) + ", ";
			color += parseInt(hex.substring(3, 5), 16) + ", ";
			color += parseInt(hex.substring(5, 7), 16) + ", ";
			color += opacity;
			color += ")";
			return color; 
		}
	</script>
</body>
</html>
