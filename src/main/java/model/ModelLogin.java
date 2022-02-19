package model;

import java.io.Serializable;

public class ModelLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private String email;
	private String Login;
	private String Senha;
	private boolean isAdmin;
	private String perfil;
	private String sexo;
	
	private fotoUser fotouser;
	
	
	
	public fotoUser getFotouser() {
		return fotouser;
	}

	public void setFotouser(fotoUser fotouser) {
		this.fotouser = fotouser;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean isNew() {
		if(this.id == 0) {/*Insere*/
			return true;
		}else if (this.id !=0 && this.id > 0) {/*Atualiza*/
			return false; 
		}
		return id == 0;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	@Override
	public String toString() {
		return "ModelLogin [id=" + id + ", nome=" + nome + ", email=" + email + ", Login=" + Login + ", Senha=" + Senha
				+ ", isAdmin=" + isAdmin + ", perfil=" + perfil + ", sexo=" + sexo + "]";
	}

	
	
}
