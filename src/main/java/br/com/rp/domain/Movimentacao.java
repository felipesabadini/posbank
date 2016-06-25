package br.com.rp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="movimentacao")
public class Movimentacao extends BaseEntity {
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="conta_id", nullable=false)
	private Conta conta;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_operacao", nullable=false)
	private TipoOperacao tipoOperacao;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@Column(name="data_movimentacao", nullable=false)
	private Timestamp dataMovimentacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_movimentacao", nullable=false)
	private TipoMovimentacao tipoMovimentacao;
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="pagamento_id")
	private Pagamento pagamento;
	
	@Column(name="codigo_banco", nullable=true)
	private String codigoBanco;
	
	@Column(name="numero_conta_destino", nullable=true)
	private Long numeroContaDestino;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao (TipoOperacao tipoOperacao) {
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

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public Long getNumeroContaDestino() {
		return numeroContaDestino;
	}

	public void setNumeroContaDestino(Long numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}
}