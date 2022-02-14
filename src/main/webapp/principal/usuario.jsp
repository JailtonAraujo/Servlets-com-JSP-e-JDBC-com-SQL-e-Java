<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1" %>


    <!DOCTYPE html>
    <html lang="en">
    <!-- head do projeto-->
    <jsp:include page="head.jsp"></jsp:include>

    <head>
        <meta charset="utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/usuario.css">
    </head>

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

                                <div class="card">
                                    <div class="card-header">
                                        <h5>Cadastro De Usuario.</h5>
                                        <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                    </div>
                                    <div class="card-block">
                                        <form class="form-material"
                                            action="<%= request.getContextPath()%>/ServletUsuarioController"
                                            method="post" id="formUser">

                                            <input type="text" name="acao" id="acao" hidden="">

                                            <div class="form-group form-default form-static-label">
                                                <input type="text" class="form-control" name="id" id="id"
                                                    readonly="readonly" value="${modelLogin.id }">
                                                <span class="form-bar"></span>
                                                <label class="float-label">ID:</label>
                                            </div>
                                            <div class="form-group form-default form-static-label">
                                                <input type="text" name="nome" id="nome" class="form-control"
                                                    required="" autocomplete="off" value="${modelLogin.nome }">
                                                <span class="form-bar"></span>
                                                <label class="float-label">Nome:</label>
                                            </div>
                                            <div class="form-group form-default form-static-label">
                                                <input type="email" name="email" id="email" class="form-control"
                                                    required="" value="${modelLogin.email }">
                                                <span class="form-bar"></span>
                                                <label class="float-label">Email (exa@gmail.com)</label>
                                            </div>
                                            <div class="form-group form-default form-static-label">
                                                <input type="text" name="login" id="login" class="form-control"
                                                    required="" autocomplete="off" value="${modelLogin.login }">
                                                <span class="form-bar"></span>
                                                <label class="float-label">Login:</label>
                                            </div>
                                            <div class="form-group form-default form-static-label">
                                                <input type="password" name="senha" id="senha" class="form-control"
                                                    required="" autocomplete="off" value="${modelLogin.senha }">
                                                <span class="form-bar"></span>
                                                <label class="float-label">Senha:</label>
                                            </div>

                                            <p id="msg">${msg }</p>

                                            <button class="btn btn-primary waves-effect waves-light"
                                                id="btn-salvar">SALVAR</button>
                                            <button type="button" class="btn btn-success waves-effect waves-light"
                                                onclick="limparCampos();">NOVO</button>
                                            <button type="button" class="btn btn-info waves-effect waves-light"
                                                onclick="criarDeleteComAjax();">EXCLUIR</button>
                                            <button type="button" class="btn btn-secondary" data-toggle="modal"
                                                data-target="#ModalUser">PESQUISAR</button>


                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="ModalUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de usuario</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-group">
                            <input type="text" id="txt-search" placeholder="Nome">
                            <button type="button" id="btn-search" onclick="">Pesquisar</button>
                        </div>
                        
                        <!--Table de visualização-->
                        <div style="height:300px; overflow: scroll;">
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
                        <span id="total-resultado"></span>
                         <!--Table de visualização-->
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
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
                        success: function (response) {
                            let json = JSON.parse(response);
                            $('#tblResultados > tbody > tr').remove();

                            for (let p = 0; p < json.length; p++) {
                                $('#tblResultados > tbody').append('<tr> <td>' + json[p].id + '</td> <td>' + json[p].nome + '</td> <td>' + json[p].login + '</td> <td><button type="button" class="btn btn-info" onclick="verEditar('+json[p].id+');" >Ver</button></td></tr>');

                            }
                            document.querySelector('#total-resultado').textContent = 'Resultados: '+json.length;
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

        </script>



    </body>

    </html>