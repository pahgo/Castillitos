<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Hello World!</h2>
	<c:if test="${not empty alianzas}">
		<table id="alianzas">
			<tbody>
				<tr>
					<th>Alianza</th>
					<th>Fecha Inicio</th>
				</tr>
				<c:forEach var="alianza" items="${alianzas}">
					<tr>
						<td><a href="alianza?id=${alianza.id}">${alianza.nombre}</a></td>
						<td>${alianza.fechaInsercion}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</c:if>
</body>
</html>
