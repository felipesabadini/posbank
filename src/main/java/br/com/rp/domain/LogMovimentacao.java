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
@Table(name="log_movimentacao")
public class LogMovimentacao extends BaseEntity {
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="conta_id", nullable=false)
	private Conta conta;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@Column(name="data_cadastro", nullable=false)
	private Timestamp dataCadastro;
	
	@Column(name="tipo_movimentacao", nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	
	@Column(name="tipo_operacao", nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoOperacao tipoOperacao;
	
//	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
//	@JoinColumn(name="conta_destino_id", nullable=true)
//	private Conta contaDestino;
	
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

//	public Conta getContaDestino() {
//		return contaDestino;
//	}
//
//	public void setContaDestino(Conta contaDestino) {
//		this.contaDestino = contaDestino;
//	}

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