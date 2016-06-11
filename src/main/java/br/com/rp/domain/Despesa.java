package br.com.rp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Despesa extends BaseEntity {
	
	private Cartao cartao;
	
	private Timestamp dataLancamento;
	
	private String descricao;
	
	private BigDecimal valor;

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Timestamp getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Timestamp dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}