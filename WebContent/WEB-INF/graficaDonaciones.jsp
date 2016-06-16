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
		<c:if
			test="${not empty nombres && not empty donados && not empty colores && not empty colores}">
			<h1 class="titleH1">Donaciones de la Alianza</h1>

			<div>
				<h1 class="titleH2">${alianza}</h1></div>
				<canvas id="myChart" width="400" height="400"></canvas>
		</c:if>
	</div>
	<script type="text/javascript">
		var nombres = ${nombres};
		var donados = ${donados};
		var colores = ${colores};
		var data = {
			datasets : [ {
				data : donados,
				backgroundColor : colores,
				label : 'Donaciones' // for legend
			} ],
			labels : nombres
		};
		var ctx = $("#myChart");
		var myChart = new Chart(ctx, {
			type : 'pie',
			data : data,
			options : {
				elements : {
					arc : {
						borderColor : "#000000"
					}
				}
			}
		});
	</script>
</body>
</html>
