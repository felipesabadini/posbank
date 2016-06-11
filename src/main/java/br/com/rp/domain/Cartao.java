package br.com.rp.domain;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class Cartao extends BaseEntity {
	
	private Conta conta;
	
	private BigDecimal limite;

	private Date dataVencimento;
	
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

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
}