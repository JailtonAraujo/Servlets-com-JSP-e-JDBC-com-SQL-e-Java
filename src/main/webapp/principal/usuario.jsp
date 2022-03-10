<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- head do projeto-->
<jsp:include page="head.jsp"></jsp:include>

<head>
<meta charset="utf-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/usuario.css">
</head>

<body>

	<!-- theme-loader -->
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper"">

			<!-- Barra De Navegação da Pagina -->
			<jsp:include page="NavBar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- Menu De Opções da Pagina -->
					<jsp:include page="MenuDeOpcoes.jsp"></jsp:include>

					<div class="pcoded-content">

						<!-- Page-header start -->
						<jsp:include page="PageHeader.jsp"></jsp:include>

						<div class="pcoded-inner-content">
							<!-- Main-body start -->

							<div class="card">
								<div class="card-header">
									<h5>Cadastro De Usuario.</h5>
									<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
								</div>
								<div class="card-block">
									<form class="form-material" enctype="multipart/form-data"
										action="<%=request.getContextPath()%>/ServletUsuarioController"
										method="post" id="formUser">

										

										<div class="form-group form-default form-static-label">
											<input type="text" class="form-control" name="id" id="id"
												readonly="readonly" value="${modelLogin.id }"> <span
												class="form-bar"></span> <label class="float-label">ID:</label>
										</div>


										<div class="form-group form-default input-group mb-4">
											<div class="input-group-prepend">

												<c:if
													test="${modelLogin.fotouser.codFoto != '' && modelLogin.fotouser.codFoto != null}">
													<a
														href="<%=request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}">
														<img alt="Imagem User" id="fotoEmBase64"
														src="${modelLogin.fotouser.codFoto }" width="70px">
													</a>
												</c:if>
												<c:if
													test="${modelLogin.fotouser.codFoto == '' || modelLogin.fotouser.codFoto == null}">
													<a
														href="<%=request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}">
														<img alt="Imagem User" id="fotoEmBase64"
														src="/projeto-jsp/assets/images/IconCliente.png"
														width="70px">
													</a>

												</c:if>

											</div>
											<input type="file" name="fileFoto" id="fileFoto"
												accept="image/*"
												onchange="visualizarImage('fotoEmBase64', 'fileFoto');"
												class="form-control-file"
												style="margin-top: 15px; margin-left: 5px">
										</div>


										<div class="form-group form-default form-static-label">
											<input  name="nome" id="nome" class="form-control"
												required="" autocomplete="off" value="${modelLogin.nome }">
											<span class="form-bar"></span> <label class="float-label">Nome:</label>
										</div>
										<div class="form-group form-default form-static-label">
											<input type="date" class="form-control" name="dataNascimento" id="dataNascimento"
												value="${modelLogin.dataNascimento }"> <span
												class="form-bar"></span> <label class="float-label">Dat.Nascimento:</label>
										</div>
										<div class="form-group form-default form-static-label">
											<input type="number" name="rendaMensal" id="rendaMensal" class="form-control"
												required="" autocomplete="off" value="${modelLogin.rendaMensal }">
											<span class="form-bar"></span> <label class="float-label">Renda Mensal:</label>
										</div>
										<div class="form-group form-default form-static-label">
											<input type="email" name="email" id="email"
												class="form-control" required=""
												value="${modelLogin.email }"> <span class="form-bar"></span>
											<label class="float-label">Email (exa@gmail.com)</label>
										</div>

										<div class="form-group form-default form-static-label">
											<select class="form-control"
												aria-label="Default select example" name="perfil">
												<option disabled="disabled">[Selecione o perfil.]</option>

												<option value="ADMIN"
													<%ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");

													if (modelLogin != null && modelLogin.getPerfil().equals("ADMIN")) {
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
													}%>>Admin</option>

												<option value="SECRETARIA"
													<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

													if (modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")) {
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
													}%>>Secret�ria</option>

												<option value="AUXILIAR"
													<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

													if (modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")) {
														out.print(" ");
														out.print("selected=\"selected\"");
														out.print(" ");
													}%>>Auxiliar</option>

											</select> <span class="form-bar"></span> <label class="float-label">Perfil:</label>
										</div>

										<div class="form-group form-default form-static-label">
											<input type="text" name="idendereco"
												value="${modelLogin.endereco.id}" hidden> <input
												type="number" name="cep" id="cep" class="form-control"
												required="" autocomplete="off"
												value="${modelLogin.endereco.cep}"> <span
												class="form-bar"></span> <label class="float-label">Cep:</label>
											<p id="alert"></p>
										</div>

										<div class="form-group form-default form-static-label">
											<input type="text" name="logradouro" id="logradouro"
												class="form-control" required="" autocomplete="off"
												value="${modelLogin.endereco.logradouro }"> <span
												class="form-bar"></span> <label class="float-label">Logradouro:</label>
										</div>

										<div class="form-group form-default form-static-label">
											<input type="text" name="complemento" id="complemento"
												class="form-control" required="" autocomplete="off"
												value="${modelLogin.endereco.complemento}"> <span
												class="form-bar"></span> <label class="float-label">Complemento:</label>
										</div>

										<div class="form-group form-default form-static-label">
											<input type="text" name="localidade" id="localidade"
												class="form-control" required="" autocomplete="off"
												value="${modelLogin.endereco.localidade }"
												readonly="readonly"> <span class="form-bar"></span>
											<label class="float-label">Localidade:</label>
										</div>

										<div class="form-group form-default form-static-label">
											<input type="text" name="bairro" id="bairro"
												class="form-control" required="" autocomplete="off"
												value="${modelLogin.endereco.bairro }"> <span
												class="form-bar"></span> <label class="float-label">Bairro:</label>
										</div>

										<div class="form-group form-default form-static-label">
											<input type="text" name="estado" id="estado"
												class="form-control" required="" autocomplete="off"
												value="${modelLogin.endereco.uf}" readonly="readonly">
											<span class="form-bar"></span> <label class="float-label">Estado:</label>
										</div>

										<div class="form-group form-default form-static-label">
											<input type="number" name="numero" id="numero"
												class="form-control" required="" autocomplete="off"
												value="${modelLogin.endereco.numero}"> <span
												class="form-bar"></span> <label class="float-label">Numero:</label>
										</div>

										<div class="form-group form-default form-static-label">
											<input type="text" name="login" id="login"
												class="form-control" required="" autocomplete="off"
												value="${modelLogin.login }"> <span class="form-bar"></span>
											<label class="float-label">Login:</label>
										</div>
										<div class="form-group form-default form-static-label">
											<input type="password" name="senha" id="senha"
												class="form-control" required="" autocomplete="off"
												value="${modelLogin.senha }"> <span class="form-bar"></span>
											<label class="float-label">Senha:</label>
										</div>

										<div class="form-group form-default form-static-label">
											<input type="radio" name="sexo" checked="checked"
												value="MASCULINO"
												<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

												if (modelLogin != null && modelLogin.getPerfil().equals("MASCULINO")) {
													out.print(" ");
													out.print("checked=\"checked\"");
													out.print(" ");
												}%>>Masculino</>


											<input type="radio" name="sexo" value="FEMININO"
												<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

											if (modelLogin != null && modelLogin.getPerfil().equals("FEMININO")) {
												out.print(" ");
												out.print("checked=\"checked\"");
												out.print(" ");
											}%>>Feminino</>
										</div>


										<p id="msg">${msg }</p>

										<button class="btn btn-primary waves-effect waves-light"
											id="btn-salvar">SALVAR</button>
										<button type="button"
											class="btn btn-success waves-effect waves-light"
											onclick="limparCampos();">NOVO</button>
										<button type="button"
											class="btn btn-info waves-effect waves-light"
											onclick="criarDeleteComAjax();">EXCLUIR</button>
											
										<c:if test="${modelLogin.id>0 }">
										<a href="<%=request.getContextPath()%>/ServletTelefone?idUser=${modelLogin.id}" class="btn btn-primary waves-effect waves-light">Telefone</a>
										</c:if>
										
										<button type="button" class="btn btn-secondary"
											data-toggle="modal" data-target="#ModalUser">PESQUISAR</button>

									</form>
								</div>
							</div>
							<div style="height: 300px; overflow: scroll; width: 80%;">
										<table class="table" id="tblResultadosTagLib">
											<thead>
												<tr>
													<th scope="col">ID:</th>
													<th scope="col">Nome:</th>
													<th scope="col">Ver:</th>
													<th scope="col">Excluir:</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${modelLogins}" var="ml">
													<tr>
														<td> <c:out value="${ml.id}"></c:out> </td>
														<td> <c:out value="${ml.nome }"></c:out> </td>
														<td> <a href="<%=request.getContextPath()%>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}" type="button" class="btn btn-info" >Ver</a> </td>
														<td> <a href="<%=request.getContextPath()%>/ServletUsuarioController?acao=exlcuirTagLib&id=${ml.id}&idEndereco=${ml.endereco.id}" type="button" class="btn btn-danger" >Excluir</a> </td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<nav aria-label="Page navigation example">
									  	<ul class="pagination">
									  	<%
									  	out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+request.getContextPath()+"/ServletUsuarioController?acao=paginar&pagina="+(0 * 5)+"\">Primeira</a></li>");
									  		int totalPagina = (int) request.getAttribute("totalPagina");
									  	
									  		int paginaAtual = 0;
									  		
									  		for(int p = 0;p < totalPagina; p++){
									  			String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina="+(p * 5);
									  			out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"\">"+(p+1)+"</a></li>");
									  		}
									  		out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+request.getContextPath()+"/ServletUsuarioController?acao=paginar&pagina="+((totalPagina-1) * 5)+"\">Ultima</a></li>");
									  	%>
									  </ul>
									</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="ModalUser" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisa de
						usuario</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container-group">
						<input type="text" id="txt-search" placeholder="Nome">
						<button type="button" id="btn-search" onclick="">Pesquisar</button>
					</div>

					<!--Table de visualização-->
					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tblResultados">
							<thead>
								<tr>
									<th scope="col">ID:</th>
									<th scope="col">Nome:</th>
									<th scope="col">Login:</th>
									<th scope="col">Ver:</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					
					<nav aria-label="Page navigation example">
						<ul class="pagination pagination-sm" id="paginacaoModel">
						</ul>
					</nav>	
					<span id="total-resultado"></span>
					<!--Table de visualização-->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="JavaScriptFile.jsp"></jsp:include>

	<script type="text/javascript">

	
        function verEditar(id){
        	let urlAction = document.querySelector('#formUser').action;
        	
        	window.location.href = urlAction + "?acao=buscarEditar&id="+id;
        }

            document.querySelector('#btn-search').addEventListener('click', (e) => {
                let nomeBusca = document.querySelector('#txt-search').value;
                let urlAction = document.querySelector('#formUser').action;

              
                    $.ajax({
                        method: 'get',
                        url: urlAction,
                        data: "nomeBusca=" + nomeBusca + "&acao=buscarComAjax",
                        success: function (response,txtStatus,xhr) {
                            let json = JSON.parse(response);
                            $('#tblResultados > tbody > tr').remove();

                            for (let p = 0; p < json.length; p++) {
                                $('#tblResultados > tbody').append('<tr> <td>' + json[p].id + '</td> <td>' + json[p].nome + '</td> <td>' + json[p].login + '</td> <td><button type="button" class="btn btn-info" onclick="verEditar('+json[p].id+');" >Ver</button></td></tr>');

                            }
                            document.querySelector('#total-resultado').textContent = 'Resultados: '+json.length;
                            
                            let total = xhr.getResponseHeader('totalPagina');
                            
                            $('#paginacaoModel > li').remove();
                            
 								for(var p = 0; p < total; p++){
 								
                            	var url = urlAction +"?nomeBusca="+nomeBusca+"&acao=buscarUserAjaxPage&pagina="+(p*5);
 								
                            	$("#paginacaoModel").append('<li class="page-item"><a class="page-link" href="#" onclick="buscarUserPagAjax(\''+url+'\')">'+(p+1)+'</a></li>');
                            	
                            }
                            
                        }
                    }).fail(function (xhr, statu, errorThrown) {
                        alert('Erro ao buscar usuario por ID:' + xhr.responseText);
                    });
                
            });

            /*
                Metodo Alternativo
            function buscarUsuario(){
                let nomeBusca = document.getElementById('txt-search').value;
                
                if(nomeBusca != "" && nomeBusca != null && nomeBusca.trim() != ""){
                    alert(nomeBusca);
                }
            }
            */

            function criarDeleteComAjax() {

                if (confirm('Deseja realmente excluir os dados?')) {

                    var urlAction = document.getElementById('formUser').action;
                    var idUser = document.getElementById('id').value;

                    $.ajax({
                        method: "get",
                        url: urlAction,
                        data: "id=" + idUser + "&acao=deletarajax",
                        success: function (response) {
                            limparCampos();
                            $('#msg').text(response);
                        }
                    }).fail(function (xhr, errorThrown) {
                        alert('erro ao deletar usuario!' + xhr.responseText);
                    });
                }
            }

            function criarDelete() {

                if (confirm('Deseja realmente excluir os dados?')) {

                    document.getElementById("formUser").method = 'get';
                    document.getElementById("acao").value = 'deletar';
                    document.getElementById("formUser").submit();
                }

            }

            function limparCampos() {
                var elementos = document.getElementById("formUser").elements;

                for (i = 0; i <= elementos.length; i++) {
                    elementos[i].value = '';
                }
            }
            
            function visualizarImage(fotoEmBase64, fileFoto) {
				let previwe = document.getElementById(fotoEmBase64);
				let fileUser = document.getElementById(fileFoto).files[0];
				let reader = new FileReader();
				
				reader.onloadend = function () {
					previwe.src = reader.result; //Carrega a foto na tela//
				};
				
				if(fileUser){
					reader.readAsDataURL(fileUser); //previwe da imagem//
				}else{
					previwe.src = '';
				}
			}

			const cep = document.querySelector('#cep');

			cep.addEventListener('blur', (e)=>{

				const options ={
					method:'GET',
					mode: 'cors',
					cache: 'default'
				}
				
				let search = cep.value;

				let validacep = /^[0-9]{8}$/;

				if(validacep.test(search)){
					
					fetch('https://viacep.com.br/ws/'+search+'/json/',options)
				.then(response=>{response.json()
					.then(function(data){

						console.log(data);
						document.querySelector('#alert').textContent = '';
						document.querySelector('#alert').style.color = '';

						document.querySelector('#localidade').value = data.localidade;
						document.querySelector('#estado').value = data.uf;


					})
					
				})
				.catch(e => {
					document.querySelector('#alert').textContent = 'cep inval�do';
					document.querySelector('#alert').style.color = 'red';
					document.querySelector('#localidade').value = '';
					document.querySelector('#estado').value = '';
					
				})
				}

				document.querySelector('#alert').textContent = 'cep inval�do';
				document.querySelector('#alert').style.color = 'red';
				document.querySelector('#localidade').value = '';
				document.querySelector('#estado').value = '';
				
				
			})
			
			function buscarUserPagAjax (url){
				let nomeBusca = document.querySelector('#txt-search').value;
				let urlAction = document.querySelector('#formUser').action;
				$.ajax({
                    method: 'get',
                    url: url,
                    success: function (response,txtStatus,xhr) {
                        let json = JSON.parse(response);
                        $('#tblResultados > tbody > tr').remove();

                        for (let p = 0; p < json.length; p++) {
                            $('#tblResultados > tbody').append('<tr> <td>' + json[p].id + '</td> <td>' + json[p].nome + '</td> <td>' + json[p].login + '</td> <td><button type="button" class="btn btn-info" onclick="verEditar('+json[p].id+');" >Ver</button></td></tr>');

                        }
                        document.querySelector('#total-resultado').textContent = 'Resultados: '+json.length;
                        
                        let total = xhr.getResponseHeader('totalPagina');
                        
                        $('#paginacaoModel > li').remove();
                        
								for(var p = 0; p < total; p++){
								
                        	var url = urlAction +"?nomeBusca="+nomeBusca+"&acao=buscarUserAjaxPage&pagina="+(p*5);
								
                        	$("#paginacaoModel").append('<li class="page-item"><a class="page-link" href="#" onclick="buscarUserPagAjax(\''+url+'\')">'+(p+1)+'</a></li>');
                        	
                        }
                        
                    }
                }).fail(function (xhr, statu, errorThrown) {
                    alert('Erro ao buscar usuario por ID:' + xhr.responseText);
                });
			}
			
			


        </script>



</body>

</html>