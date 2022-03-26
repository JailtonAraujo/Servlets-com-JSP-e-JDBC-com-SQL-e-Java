<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
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
                                        <div class="row" style="flex-direction: column">
                                           
                                            <h1>Sistema de controle de usuarios em JSP!</h1>
                                            </br>
                                             <h3>Desenvolvedor: Jailton de Araujo Santos </h3>
                                             </br>
                                             <div class="contato" style="display: flex; ">
                                             	<a href="https://github.com/JailtonAraujo" style="margin-right: 10px"><img alt="GitHub" src="<%=request.getContextPath()%>\assets\images\github.png"></a>
                   
                                            	<a href="https://www.linkedin.com/in/jailton-araujo-b602041a2/" style="margin-right: 10px"><img alt="linkedin" src="<%=request.getContextPath()%>\assets\images\linkedin.png"></a>
                                            	
                                            	<a href="https://www.instagram.com/jailton9040/"><img alt="instagram" src="<%=request.getContextPath()%>\assets\images\instagram.png"></a>
                                            
                                             </div>
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
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
