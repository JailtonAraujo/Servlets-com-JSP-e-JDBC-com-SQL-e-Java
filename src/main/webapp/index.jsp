<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">


	<link rel="icon" href="assets/images/gato-preto.png" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/loginstyle.css">
	 
<title>Projeto JSP</title>
</head>
<body>

	<div class = "main">
	<form action="<%=request.getContextPath()%>/ServletLogin" method="post" class="needs-validation" novalidate>
	 
	<h2>Login</h2>
	<input type="hidden" value="<%= request.getParameter("url")%>" name="url">
		<div class="md-3">
		    <label for="exampleInputEmail1" class="form-label">Login</label>
		    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="login" required>
		    <div class="invalid-feedback">
      			Campo Obrigatorio!!
    		</div>
    		<div class="valid-feedback">
      			Ok
    		</div>
  		</div>
		<div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">Password</label>
		    <input type="password" class="form-control" id="exampleInputPassword1" name="senha" required=>
		    <div class="invalid-feedback">
      			Campo Obrigatorio!!
    		</div>
    		<div class="valid-feedback">
      			Ok
    		</div>
		</div>
		
		<p>${msg}</p>
  <button type="submit" class="btn btn-primary">Entrar</button>
  <div class="div-cadastro">
  	<h5>É novo? <a href="<%= request.getContextPath()%>/cadastrodevisitantes.jsp" >Cadastre-se!</a></h5>
  </div>
	</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	
	<script type="text/javascript">(function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  var forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
		})()</script>
</body>
</html>