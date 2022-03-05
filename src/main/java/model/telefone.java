package model;

public class telefone {

	private int idTelefone;
	private String telefone;
	private int idUsuario_pai;
	private int idusuario_cad;

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

	public int getIdUsuario_pai() {
		return idUsuario_pai;
	}

	public void setIdUsuario_pai(int idUsuario_pai) {
		this.idUsuario_pai = idUsuario_pai;
	}

	public int getIdusuario_cad() {
		return idusuario_cad;
	}

	public void setIdusuario_cad(int idusuario_cad) {
		this.idusuario_cad = idusuario_cad;
	}

}
