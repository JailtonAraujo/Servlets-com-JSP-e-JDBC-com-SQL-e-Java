package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import model.ModelLogin;


@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
    
    public ServletLogin() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			doPost(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
		
		if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty() ) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if(daoLoginRepository.ValidarAutenticacao(modelLogin)) {
				
				modelLogin = daoUsuarioRepository.ConsultarUsuarioLogado(login);
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				request.getSession().setAttribute("perfil", modelLogin.getPerfil());
				request.getSession().setAttribute("imagemUser", modelLogin.getFotouser().getCodFoto());
				
				
				if(url == null || url.equals("null")) {
					url = "principal/principal.jsp";
				}
				
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);//aqui
				redirecionar.forward(request, response);
				
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Login ou Senha Incorretos. Confira os Dados");
				redirecionar.forward(request, response);
			}	
			
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			//request.setAttribute("msg", "Os Campos Não Foram Preenchidos Corretamente!");
			redirecionar.forward(request, response);
			
		}
		
		}catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", "Causa da Exceção: "+e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}
