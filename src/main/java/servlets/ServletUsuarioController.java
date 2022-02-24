package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;
import model.endereco;
import model.fotoUser;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;

@MultipartConfig
@WebServlet( urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletUtilGeneric implements Servlet {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletUsuarioController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String id = request.getParameter("id");

				daoUsuarioRepository.deletar(id);
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListPaginado(this.getUsuario_id(request), 0);
				
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "Excluido com sucesso!");

				request.setAttribute("total", daoUsuarioRepository.totalPagina(this.getUsuario_id(request)));
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String id = request.getParameter("id");

				daoUsuarioRepository.deletar(id);

				response.getWriter().write("Excluido com sucesso!");
				request.setAttribute("msg", "Excluido com sucesso!");

			}

			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarComAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<ModelLogin> dadosJasonUser = daoUsuarioRepository.consultarUsuarioList(nomeBusca, super.getUsuario_id(request));

				ObjectMapper objectMapper = new ObjectMapper();

				String Json = objectMapper.writeValueAsString(dadosJasonUser);

				response.getWriter().write(Json);

			}

			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.buscarUsuarioPorId(id, super.getUsuario_id(request));
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListPaginado(this.getUsuario_id(request), 0);
				
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("msg", "Usuario em edi��o");
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("total", daoUsuarioRepository.totalPagina(this.getUsuario_id(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) { /*PREPARANDO DOWNLOAD DA FOTO DO USUARIO*/
				String idUser = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.buscarUsuarioPorId(idUser, super.getUsuario_id(request));
				
				if(modelLogin.getFotouser().getCodFoto() != null && !modelLogin.getFotouser().getCodFoto().isEmpty() && modelLogin.getFotouser().getExtensao() != null && !modelLogin.getFotouser().getExtensao().isEmpty()) {
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo."+modelLogin.getFotouser().getExtensao());
					response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotouser().getCodFoto().split("\\,")[1]));
				}
				
			}
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListPaginado(this.getUsuario_id(request), 0);
				
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("msg", "Usuarios carregados");
				request.setAttribute("total", daoUsuarioRepository.totalPagina(this.getUsuario_id(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("exlcuirTagLib")) {
				
				String id = request.getParameter("id");
				
				daoUsuarioRepository.deletar(id);
				request.setAttribute("msg", "Excluido com Sucesso");
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListPaginado(this.getUsuario_id(request), 0);
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			else {
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListPaginado(this.getUsuario_id(request), 0);
				
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", "Causa da Exce��o: " + e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Opera��o Realizada com Sucesso!";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			
			String idendereco = request.getParameter("idendereco");
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String complemento = request.getParameter("complemento");
			String localidade = request.getParameter("localidade");
			String bairro = request.getParameter("bairro");
			String numero = request.getParameter("numero");
			String uf = request.getParameter("estado");
			
			endereco endereco = new endereco();
			endereco.setId(Integer.valueOf(idendereco));
			endereco.setCep(Long.valueOf(cep));
			endereco.setLogradouro(logradouro);
			endereco.setLocalidade(localidade);
			endereco.setComplemento(complemento);
			endereco.setBairro(bairro);
			endereco.setUf(uf);
			endereco.setNumero(Integer.valueOf(numero));

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEndereco(endereco);
			modelLogin.setId(id != null & !id.isEmpty() ? Long.parseLong(id) : 0);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			
			if(ServletFileUpload.isMultipartContent(request)) {
				Part part = request.getPart("fileFoto");//Pega a foto da tela
				
				if(part.getSize() > 0) {
				
				byte[] foto = IOUtils.toByteArray(part.getInputStream());//Converte para byte//
				String imagemBase64 = "data:image/" +part.getContentType().split("\\/")[1]+";base64,"+ new Base64().encodeBase64String(foto);
				fotoUser fotouser = new fotoUser();
				fotouser.setCodFoto(imagemBase64);
				fotouser.setExtensao(part.getContentType().split("\\/")[1]);
				modelLogin.setFotouser(fotouser);
				}
				
			}

			if (daoUsuarioRepository.ValidarLogin(modelLogin.getLogin()) == true && modelLogin.getId() == 0) {
				msg = "J� existe um usuario com o mesmo login, informe outro login!";
			} else {

				if (modelLogin.isNew()) {
					msg = "Salvo com sucesso!";
				} else {
					msg = "Atualizado com sucesso!";
				}
				modelLogin = daoUsuarioRepository.Salvar(modelLogin,super.getUsuario_id(request));
			}

			List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListPaginado(this.getUsuario_id(request), 0);
			
			request.setAttribute("modelLogins", modelLogins);
			
			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("total", daoUsuarioRepository.totalPagina(this.getUsuario_id(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", "Causa da Exce��o: " + e.getMessage());
			requestDispatcher.forward(request, response);
		}

	}

}
