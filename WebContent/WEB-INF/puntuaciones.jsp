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
		<h1 class="titleH1">Puntuaciones de Jugadores de la Alianza</h1>

		<div>
			<h1 class="titleH2">${alianza.nombre}</h1>
		</div>

		<c:if test="${not empty jugadores}">
			<form action="guardarNuevasPuntuaciones?id=${alianza.id}" method="post">
				<input type="hidden" id="content" name="content" value="">
				<table id="jugadores" class="sorTable">
					<thead>
						<tr>
							<th><span>Nombre</span></th>
							<!-- <th><span>Alias</span></th> -->
							<th><span>Puntos Previos</span></th>
							<th><span>Puntos Actuales</span></th>
							<th><span>Diferencia</span></th>
							<th><span>Fragmentos Previos</span></th>
							<th><span>Fragmentos Actuales</span></th>
							<th><span>Diferencia</span></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="jugador" items="${jugadores}">
							<tr id="${jugador.id}" class="jugador">
								<td class="lalign">${jugador.nombre}</td>
								<!-- <td>${jugador.alias}</td> -->
								<td><input id="puntosPrevios_${jugador.id}" value="${jugador.puntos}" disabled="disabled"></td>
								<td><input id="puntos_${jugador.id}" value="" onchange="actualizarDiferencia('puntos', ${jugador.id})"></td>
								<td><input id="diferenciapuntos_${jugador.id}" value="" disabled="disabled"></td>
								<td><input id="fragmentosPrevios_${jugador.id}" value="${jugador.fragmentos}" disabled="disabled"></td>
								<td><input id="fragmentos_${jugador.id}" value="" onchange="actualizarDiferencia('fragmentos', ${jugador.id})"></td>
								<td><input id="diferenciafragmentos_${jugador.id}" value="" disabled="disabled"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input id="Guardar" type="submit" value="Guardar" onclick="guardarClicked()">

			</form>
		</c:if>
		<c:if test="${empty jugadores}">
			Vaya mierda de alianza...
		</c:if>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#jugadores').tablesorter();
		});
		function actualizarDiferencia(campo, id) {
			$('#diferencia'+campo+'_'+id).val($('#'+campo+'_'+id).val() - $('#'+campo+'Previos_'+id).val());
			$('#'+campo+'_'+id).attr('value', $('#'+campo+'_'+id).val());
			$('#diferencia'+campo+'_'+id).attr('value', $('#diferencia'+campo+'_'+id).val());
		}
		function guardarClicked() {
			var content = "[";
			$('tr[class=jugador]').each(function () {
				var id = $(this).attr('id');
				content += 'jugador[id=' + id + ',';
				content += 'puntos=' + $('#puntos_' + id).attr('value') + ',';
				content += 'puntosPrevios=' + $('#puntosPrevios_' + id).attr('value') + ',';
				content += 'fragmentos=' + $('#fragmentos_' + id).attr('value') + ',';
				content += 'fragmentosPrevios=' + $('#fragmentosPrevios_' + id).attr('value');
				content += ']';
			});
			content += ']';
			$('#content').val(content);
		} 
	</script>

</body>
</html>
