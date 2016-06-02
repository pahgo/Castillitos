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
		<h1>Jugadores de la Alianza</h1>

		<c:if test="${not empty jugadores}">
			<table id="keywords" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th><span>Nombre</span></th>
						<!-- <th><span>Alias</span></th> -->
						<th><span>Puntos</span></th>
						<th><span>Fragmentos</span></th>
						<th><span>Proporción</span></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="jugador" items="${jugadores}">
						<tr>
							<td class="lalign"><a href="jugador?id=${jugador.id}">${jugador.nombre}</a></td>
							<!-- <td>${jugador.alias}</td> -->
							<td>${jugador.puntos}</td>
							<td>${jugador.fragmentos}</td>
							<td>${jugador.proporcion}%</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${empty jugadores}">
		Vaya mierda de alianza...
	</c:if>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#keywords').tablesorter();
		});
	</script>

</body>
</html>
