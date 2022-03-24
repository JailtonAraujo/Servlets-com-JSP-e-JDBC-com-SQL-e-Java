<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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

														<h4 class="sub-title">Rel.Usuario</h4>

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="get" id="formRelGrafico">

																<input type="hidden" name="acao" id="acaoRelatorioImprimirTipo" value="imprimirRelatorioUser">
																
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
																	<button type="button" onclick="gerarGrafico();" class="btn btn-primary mb-2">GERAR GRÁFICO</button>
																</div>
															</div>
														</form>
														<div>
														<canvas id="myChart"></canvas>
													</div>
													</div>
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
	
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script type="text/javascript">

	
	var myChart = new Chart(document.getElementById('myChart'));
	
		function gerarGrafico(){
			
			let urlAction = document.querySelector('#formRelGrafico').action;
			let dataInicial = document.querySelector('#dataInicial').value;
			let dataFinal = document.querySelector('#dataFinal').value;
			
			
			$.ajax({
                method: "get",
                url: urlAction,
                data: "dataInicial=" +dataInicial+'&dataFinal='+dataFinal+'&acao=graficoSalario',
                success: function (response) {
                	
                	let json = JSON.parse(response);
                	
                	myChart.destroy();
                    
                    	myChart = new Chart(
        				    document.getElementById('myChart'),
        				    {
        					    type: 'line',
        					    data: {
        						    labels: json.listPerfil,
        						    datasets: [{
        						      label: 'Gráfrico de média salario por tipo',
        						      backgroundColor: 'rgb(255, 99, 132)',
        						      borderColor: 'rgb(255, 99, 132)',
        						      data: json.listMediaSalarial,
        						    }]
        						  },
        					    options: {}
        					  }
        				  );
                }
            }).fail(function (xhr, errorThrown) {
                alert('erro ao buscar dados para grafico!' + xhr.responseText);
            });
			
			
			
		}
	
		function imprimirHtml() {
			document.querySelector('#acaoRelatorioImprimirTipo').value = 'imprimirRelatorioUser';
			document.querySelector('#formRelUsuario').submit();
			
			
		}
		
		function imprimirPDF() {
			document.querySelector('#acaoRelatorioImprimirTipo').value = 'imprimirRelatorioPDF';
			document.querySelector('#formRelUsuario').submit();
			
			
		}
	</script>
</body>

</html>
