<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Hello World!</h2>
	<c:if test="${not empty jugadores}">
		<table id="alianzas">
			<tbody>
				<tr>
					<th>Nombre</th>
					<th>Alias</th>
					<th>Fecha de registro</th>
				</tr>
				<c:forEach var="jugador" items="${jugadores}">
					<tr>
						<td><a href="jugador/${jugador.id}">${jugador.nombre}</a></td>
						<td>${jugador.alias}</td>
						<td>${jugador.fechaRegistro}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty jugadores}">
		Vaya mierda de alianza...
	</c:if>
</body>
</html>