package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

import java.io.IOException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletUtilGeneric {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();
	
	public ServletTelefone() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			
		String acao = request.getParameter("acao");
		
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluir")) {
			
		String idTelefone = request.getParameter("id");
		
		daoTelefoneRepository.deleteFone(Integer.parseInt(idTelefone));
		
		String userPai = request.getParameter("userPai");
		
		ModelLogin usuario = daoUsuarioRepository.buscarUsuarioPorId(Long.parseLong(userPai));
		
		request.setAttribute("modelLogin", usuario);
		
		List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone((int) usuario.getId());
		
		request.setAttribute("modelTelefones", modelTelefones);
		
		request.setAttribute("msg", "Excluido com sucesso!");
		
		request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		
		return;
			
		}
			
		String idUser = request.getParameter("idUser");
		
		if(idUser != null && !idUser.isEmpty()) {
		
			
			ModelLogin usuario = daoUsuarioRepository.buscarUsuarioPorId(Long.parseLong(idUser));
			
			request.setAttribute("modelLogin", usuario);
			
			List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone((int) usuario.getId());
			
			request.setAttribute("modelTelefones", modelTelefones);
			
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		
		}else {
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListPaginado(this.getUsuario_id(request), 0);
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUsuario_id(request)));
			request.setAttribute("modelLogins", modelLogins);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/*============================================================================*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		String idUsuarioPai = request.getParameter("id");
		String numeroTelefone = request.getParameter("numero");
		
		ModelTelefone modelTelefone = new ModelTelefone();
		modelTelefone.setTelefone(numeroTelefone);
		modelTelefone.setUsuario_pai(daoUsuarioRepository.buscarUsuarioPorId(Long.parseLong(idUsuarioPai)));
		modelTelefone.setUsuario_cad(super.getUsuarioLogado(request));
		
		daoTelefoneRepository.gravarTelefone(modelTelefone);
		
		List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone(Integer.parseInt(idUsuarioPai));
		
		ModelLogin usuario = daoUsuarioRepository.buscarUsuarioPorId(Long.parseLong(idUsuarioPai));
		
		request.setAttribute("modelLogin", usuario);
		request.setAttribute("modelTelefones", modelTelefones);
		request.setAttribute("msg", "Salvo com sucesso!");
		request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
