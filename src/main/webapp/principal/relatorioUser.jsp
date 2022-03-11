<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

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

														<h4 class="sub-title">Rel.Usuario</h4>

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="get" id="formRelUsuario">

																<input type="hidden" name="acao" value="imprimirRelatorio">
																
															<div class="form-row align-items-center">
															
																<div class="col-auto">
																	<label class="sr-only" for="Data Inicial">Data Inicial:</label>
																	<input type="date" class="form-control mb-2"
																		id="dataInicial" name="dataInicial" value="${dataInicial }">
																</div>
																
																<div class="col-auto">
																	<label class="sr-only" for="Data Final">Data Final:</label>
																	<div class="input-group mb-2">
																		<input type="date" class="form-control"
																			id="dataFinal" name="dataFinal" value="${dataFinal}">
																	</div>
																</div>
																
																
																<div class="col-auto">
																	<button type="submit" class="btn btn-primary mb-2">IMPRIMIR RELATORIO</button>
																</div>
															</div>
														</form>
													</div>
												</div>
												<div style="height: 300px; overflow: scroll; width: 80%;">
													<table class="table" id="tblResultadosTagLib">
														<thead>
															<tr>
																<th scope="col">ID:</th>
																<th scope="col">Nome:</th>
																<th scope="col">Perfil:</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${listUser}" var="user">
																<tr>
																	<td><c:out value="${user.id}"></c:out></td>
																	<td><c:out value="${user.nome}"></c:out></td>
																	<td><c:out value="${user.perfil}"></c:out></td>
																</tr>
																
																<c:forEach items="${user.listaDeTelefones}" var="fone">
																	<tr>
																	<td/>
																		<td style="font-size: 10px;"><c:out value="${fone.telefone}"></c:out> </td>
																	</tr>
																</c:forEach>
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
