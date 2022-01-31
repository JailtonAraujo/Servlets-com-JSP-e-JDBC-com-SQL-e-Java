package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

import dao.DAOUsuarioRepository;

@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet implements Servlet{
	private static final long serialVersionUID = 1L;
       
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
  
    public ServletUsuarioController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
			String msg = "Operação Realizada com Sucesso!";
			
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setId(id != null & !id.isEmpty() ? Long.parseLong(id) : 0);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		
		if(daoUsuarioRepository.ValidarLogin(modelLogin.getLogin()) == true && modelLogin.getId() == 0 ) {
			msg = "Já existe um usuario com o mesmo login, informe outro login!";
		}else {
			
			modelLogin = daoUsuarioRepository.Salvar(modelLogin);
		}
		
	
		request.setAttribute("msg", msg);
		request.setAttribute("modelLogin", modelLogin);
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	
		}catch(Exception e) {
			e.printStackTrace();
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", "Causa da Exceção: " + e.getMessage());
			requestDispatcher.forward(request, response);
		}
	
	}

}
