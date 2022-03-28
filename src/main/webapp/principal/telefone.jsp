<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" style=" scroll-behavior: auto;">

<!-- head do projeto-->
<jsp:include page="head.jsp"></jsp:include>

<body>

	<!-- theme-loader -->
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

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
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">

											<div class="col-sm-12">

												<div class="card">

													<div class="card-block">
													
													<h4 class="sub-title">Cad.Telefone</h4>
													
													<form class="form-material" action="<%=request.getContextPath()%>/ServletTelefone" method="post" id="formTelefone">

														<div class="form-group form-default form-static-label">
															<input type="text" class="form-control" name="id" id="id"
																readonly="readonly" value="${modelLogin.id }"> <span
																class="form-bar"></span> <label class="float-label">ID:</label>
														</div>
														
														<div class="form-group form-default form-static-label">
															<input type="text" class="form-control" name="nome" id="nome"
																readonly="readonly" value="${modelLogin.nome }"> <span
																class="form-bar"></span> <label class="float-label">NOME:</label>
														</div>
															
															<c:if test="${perfil == 'ADMIN'}">
															<div class="form-group form-default form-static-label">
																<input type="number" class="form-control" name="numero"
																	id="numero" required="required"> <span
																	class="form-bar"></span> <label class="float-label">NUMERO
																	DO TELEFONE:</label>
															</div>
																
																	<button class="btn btn-primary waves-effect waves-light"
																id="btn-salvar">SALVAR</button>
																</c:if>
														</form>
														<span id="msg">${msg}</span>
														</div>
														
												</div>

												<div style="height: 300px; overflow: scroll; width: 80%;">
													<table class="table" id="tblResultadosTagLib">
														<thead>
															<tr>
																<th scope="col">ID:</th>
																<th scope="col">Numero:</th>
																<c:if test="${perfil == 'ADMIN'}">
																<th scope="col">Excluir:</th>
																</c:if>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${modelTelefones }" var="f">
																<tr>
																	<td><c:out value="${f.idTelefone}"></c:out></td>
																	<td><c:out value="${f.telefone }"></c:out></td>
																	<c:if test="${perfil == 'ADMIN'}">
																	<td><a
																		href="<%=request.getContextPath()%>/ServletTelefone?acao=excluir&id=${f.idTelefone}&userPai=${modelLogin.id}"
																		type="button" class="btn btn-danger">Excluir</a></td>
																	</c:if>	
																	
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="JavaScriptFile.jsp"></jsp:include>
</body>

</html>
