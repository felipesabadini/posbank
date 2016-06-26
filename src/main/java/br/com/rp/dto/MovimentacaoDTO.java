package br.com.rp.dto;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class MovimentacaoDTO implements Serializable {
	
	private Long contaId;
	
	private BigDecimal valor;
	
	private Long contaDestinoId;
	
	private String codigoBancoDestino;
	
	private Long numeroContaDestino;
	
	private Long pagamentoId;
	
	private String cmc7;

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getContaDestinoId() {
		return contaDestinoId;
	}

	public void setContaDestinoId(Long contaDestinoId) {
		this.contaDestinoId = contaDestinoId;
	}

	public String getCodigoBancoDestino() {
		return codigoBancoDestino;
	}

	public void setCodigoBancoDestino(String codigoBancoDestino) {
		this.codigoBancoDestino = codigoBancoDestino;
	}

	public Long getNumeroContaDestino() {
		return numeroContaDestino;
	}

	public void setNumeroContaDestino(Long numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}

	public Long getPagamentoId() {
		return pagamentoId;
	}

	public void setPagamentoId(Long pagamentoId) {
		this.pagamentoId = pagamentoId;
	}

	public String getCmc7() {
		return cmc7;
	}

	public void setCmc7(String cmc7) {
		this.cmc7 = cmc7;
	}
}