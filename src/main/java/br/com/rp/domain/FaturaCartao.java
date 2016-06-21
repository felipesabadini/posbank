package br.com.rp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="fatura_cartao")
public class FaturaCartao extends BaseEntity {
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="cartao_id", nullable=false)
	private Cartao cartao;
	
	@Column(name="data_vencimento", nullable=false)
	private Date dataVencimento;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@Column(name="mes_referencia", nullable=false)
	private Integer mesReferencia;
	
	@Column(name="ano_referencia", nullable=false)
	private Integer anoReferencia;
	
	@Column(name="data_pagamento", nullable=true)
	private Timestamp dataPagamento;
	
	@Column(name="valor_pago", nullable=true)
	private BigDecimal valorPago;

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(Integer mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public Integer getAnoReferencia() {
		return anoReferencia;
	}

	public void setAnoReferencia(Integer anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	public Timestamp getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Timestamp dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}	
}