package servlets;

import java.io.Serializable;



import dao.DAOUsuarioRepository;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ModelLogin;

public class ServletUtilGeneric extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public long getUsuario_id(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.ConsultarUsuarioLogado(usuarioLogado).getId();
		
		
	}
	
	public ModelLogin getUsuarioLogado (HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.ConsultarUsuarioLogado(usuarioLogado);
		
		
	}
}
