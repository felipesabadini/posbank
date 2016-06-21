package br.com.rp.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="cartao")
public class Cartao extends BaseEntity {
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="conta_id")
	private Conta conta;
	
	@Column(name="limite")
	private BigDecimal limite;

	@Column(name="dia_vencimento")
	private Integer diaVencimento;
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
}