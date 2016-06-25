package br.com.rp.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity
@Table(name="conta")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Conta extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name="agencia_id")
	private Agencia agencia;
	
	@Column(name="numero", nullable=false)
	private Integer numero;

	@Enumerated(EnumType.STRING)
	@Column(name="tipo_conta")
	private TipoConta tipoConta;
	
	@Column(name="saldo", nullable=false)
	private BigDecimal saldo;
	
	@Column(name="limite", nullable=false)
	private BigDecimal limite;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cliente_id", nullable=false)
	private Cliente cliente;

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}