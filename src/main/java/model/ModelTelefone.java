package model;

public class ModelTelefone {

	private int idTelefone;
	private String telefone;
	private ModelLogin Usuario_pai;
	private ModelLogin usuario_cad;

	public int getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(int idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public ModelLogin getUsuario_pai() {
		return Usuario_pai;
	}

	public void setUsuario_pai(ModelLogin usuario_pai) {
		Usuario_pai = usuario_pai;
	}

	public ModelLogin getUsuario_cad() {
		return usuario_cad;
	}

	public void setUsuario_cad(ModelLogin usuario_cad) {
		this.usuario_cad = usuario_cad;
	}

}
