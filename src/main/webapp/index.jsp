<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Estudando JSP</title>

<link rel="stylesheet" type="text/css" href="CSS/LoginStyle.css" >

</head>
<body>
	<form action="ServletLogin" method="post">
	<input type="hidden" value="<%= request.getParameter("url")%>" name="url">


		<fieldset class="container">

			<h2>LOGIN</h2>

			<label>Login:</label>
			 <input name="login" type="text" placeholder="Login" id="Login"> 
			 
			 <label>Senha:</label> 
			 <input name="senha" type="password" placeholder="Senha" id="Senha"> 
			 
			 <h4> ${msg}</h4>
			 
			 <input type="submit" value="Logar" id="logar" >
			 
		</fieldset>
	</form>
</body>
</html>