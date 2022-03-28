package servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

import dao.DAOUsuarioRepository;

@WebServlet (urlPatterns = {"/ServletVisitante"})
public class ServletVisitante extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
    public ServletVisitante() {
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String nome =  request.getParameter("nome");
		
		try {
			
			 ModelLogin visitante = new ModelLogin();
			 visitante.setNome(nome);
			 visitante.setEmail(email);
			 visitante.setSenha(senha);
			 visitante.setLogin(login);
			 visitante.setPerfil("VISITANTE");
			 visitante.setSexo("MASCULINO");
			 String msg = "";
			 
			if(daoUsuarioRepository.ValidarLogin(login) == false) {
				daoUsuarioRepository.cadastrarUsuarioVisitante(visitante);
				msg = "Visitante cadastrado com sucesso!";
			}else {
				msg = "Ja existe um visitante com essas informa��es";
			}
			 
			 
			 request.setAttribute("msg", msg);
			 request.getRequestDispatcher("cadastrodevisitantes.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}

}
