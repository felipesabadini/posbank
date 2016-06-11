package br.com.rp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Movimentacao extends BaseEntity {
	
	private TipoOperacao tipoOperacao;
	
	private BigDecimal valor;
	
	private Timestamp dataMovimentacao;
	
	private String descricao;

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperaacao (TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Timestamp getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Timestamp dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}