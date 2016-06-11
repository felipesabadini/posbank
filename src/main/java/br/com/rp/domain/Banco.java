package br.com.rp.domain;

@SuppressWarnings("serial")
public class Banco extends BaseEntity {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}