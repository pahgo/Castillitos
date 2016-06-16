<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value="/resources/css/styles.css"/>">
<script src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
</head>
<body>
	<div id="wrapper">
		<h1 class="titleH1">PUM!</h1>
		<img src="<c:url value="/resources/images/Evolved_Skull_Knight_Icon.png"/>" alt="Estamos trabajando en ello">
		<span>${errors}</span>
	</div>
</body>
</html>
