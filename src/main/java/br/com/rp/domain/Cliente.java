package br.com.rp.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome")
	private String nome;
	
	@AttributeOverride(name="value", column=@Column(name="cpf"))
	private Cpf cpf;
	
	private Endereco endereco;
	
	@AttributeOverride(name="value", column=@Column(name="email"))
	private Email email;
	
	@Enumerated(EnumType.STRING)
	private SituacaoCliente situacao;
	
	public Cliente() {
		this.setSituacao(SituacaoCliente.INATIVO);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public SituacaoCliente getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoCliente situacao) {
		this.situacao = situacao;
	}
}