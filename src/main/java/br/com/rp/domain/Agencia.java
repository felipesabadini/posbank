package br.com.rp.domain;

@SuppressWarnings("serial")
public class Agencia extends BaseEntity {
	
	private String nome;
	
	private Banco banco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
}