package br.com.rp.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "funcionario")
public class Funcionario extends BaseEntity {

	@Column(name = "nome", nullable = false, length = 200)
	@Size(min = 2, max = 200)
	private String nome;

	@AttributeOverride(name = "value", column = @Column(name = "cpf"))
	private Cpf cpf;

	private Endereco endereco;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="cargo")
	private Cargo cargo;

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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}