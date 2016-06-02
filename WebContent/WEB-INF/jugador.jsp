<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Historial!</h2>
	<c:if test="${not empty ingresos}">
		<table id="jugador">
			<tbody>
				<tr>
					<th>Jugador</th>
					<th>Fecha</th>
					<th>Puntos</th>
					<th>Puntos la otra vez</th>
					<th>Fragmentos</th>
					<th>Fragmentos la otra vez</th>
				</tr>
				<c:forEach var="ingreso" items="${ingresos}">
					<tr>
						<td>${nombre}</td>
						<td>${ingreso.fecha}</td>
						<td>${ingreso.puntos}</td>
						<td>${ingreso.puntosPrevios}</td>
						<td>${ingreso.fragmentos}</td>
						<td>${ingreso.fragmentosPrevios}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>
