package model;

import java.util.Objects;

public class endereco {

	private long cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private int numero;

	public endereco() {
	}

	public endereco(long cep, String logradouro, String complemento, String bairro, String localidade, String uf,
			int numero) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.numero = numero;
	}

	public long getCep() {
		return cep;
	}

	public void setCep(long cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, localidade, logradouro, numero, uf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		endereco other = (endereco) obj;
		return Objects.equals(bairro, other.bairro) && cep == other.cep && Objects.equals(localidade, other.localidade)
				&& Objects.equals(logradouro, other.logradouro) && numero == other.numero
				&& Objects.equals(uf, other.uf);
	}

	@Override
	public String toString() {
		return "endereco [cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro + ", localidade="
				+ localidade + ", uf=" + uf + ", numero=" + numero + "]";
	}

}
