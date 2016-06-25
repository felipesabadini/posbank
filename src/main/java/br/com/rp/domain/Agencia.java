package br.com.rp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.rp.domain.Banco;
import br.com.rp.domain.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="agencia")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Agencia extends BaseEntity {
	
	private String nome;
	
	@ManyToOne(cascade={CascadeType.ALL})
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