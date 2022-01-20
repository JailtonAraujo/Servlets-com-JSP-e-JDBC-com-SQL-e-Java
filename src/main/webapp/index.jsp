<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Estudando JSP</title>
</head>
<body>
	
	<h1>Testando Envio!</h1>
	
	<form action="ServletLogin" method="post" onsubmit="" >
	
	<label>Nome:</label>
	<input name="nome">
	<label>Idade:</label>
	<input name="idade" type="date">
	
	<input type="submit" value="Enviar">
	</form>

	<script type="text/javascript">

	</script>
</body>
</html>