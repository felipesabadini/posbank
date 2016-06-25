package br.com.rp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.rp.domain.Banco;
import br.com.rp.domain.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="agencia")
public class Agencia extends BaseEntity {
	
	private String nome;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="banco_id")
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