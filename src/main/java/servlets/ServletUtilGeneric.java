package servlets;

import java.io.Serializable;



import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletUtilGeneric extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public long getUsuario_id(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.ConsultarUsuarioLogado(usuarioLogado).getId();
		
		
	}
}
