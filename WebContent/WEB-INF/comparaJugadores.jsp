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
		<input id="toggle" type="button" value="Comparar Fragmentos" onclick="toggle();">
		<c:if test="${not empty jugadorA && not empty jugadorB}">
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
		var ctx = $("#myChart");
		var myChart = new Chart(ctx,
				{
					type : 'line',
					data : {
						labels : [${fechas}],
						datasets : [ {
							label : "${mode} ${jugadorA.nombre}",
							data : [${puntosA}],
							backgroundColor : [ 'rgba(255, 99, 132, 0.2)' ],
							borderColor : [ 'rgba(255,99,132,1)' ],
							borderWidth : 1
						}, 
						{
							label : "${mode} ${jugadorB.nombre}",
							data : [${puntosB}],
							backgroundColor : [ 'rgba(99, 255, 132, 0.2)' ],
							borderColor : [ 'rgba(99, 255, 132,1)' ],
							borderWidth : 1
						}]
					},
					options : {
						scales : {
							yAxes : [ {
								ticks : {
									beginAtZero : true
								}
							} ]
						}
					}
				});
	</script>
</body>
</html>
