<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value="/resources/css/styles.css"/>">
<script src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.tablesorter.min.js" />"></script>
</head>
<body>
	<div id="wrapper">
		<h1 class="titleH1">${jugador.nombre }</h1>
		<c:if test="${not empty ingresos}">
			<table id="ingresos" class="sorTable">
				<thead>
					<tr>
						<th><span>Jugador</span></th>
						<th><span>Fecha</span></th>
						<th><span>Puntos</span></th>
						<th><span>Diferencia</span></th>
						<th><span>Fragmentos</span></th>
						<th><span>Diferencia</span></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ingreso" items="${ingresos}">
						<tr>
							<td>${jugador.nombre}</td>
							<td>${ingreso.fecha}</td>
							<td>${ingreso.puntos}</td>
							<td class="evaluatePos">${ingreso.puntosPrevios}</td>
							<td>${ingreso.fragmentos}</td>
							<td>${ingreso.fragmentosPrevios}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#ingresos').tablesorter();
		});
		$(function() {
			$('.evaluatePos').each(function() {
				if(Number($(this).html()) > 0) {
					$(this).attr("style", "background:green; color: aqua;");
				}
				else if(Number($(this).html()) < 0) {
					$(this).attr("style", "background:red; color: aqua;");
				}
			});
		});
	</script>
</body>
</html>
