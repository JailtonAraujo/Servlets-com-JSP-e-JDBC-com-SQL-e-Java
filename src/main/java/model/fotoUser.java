package model;

import java.io.Serializable;

public class fotoUser implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String codFoto;
	private String extensao;

	public String getCodFoto() {
		return codFoto;
	}

	public void setCodFoto(String codFoto) {
		this.codFoto = codFoto;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

}
