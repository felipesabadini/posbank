package br.com.rp.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import br.com.rp.util.Util;

@SuppressWarnings("serial")
@Entity
@Table(name="agendamento")
public class Agendamento extends  BaseEntity {
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="conta_id", nullable=false)
	private Conta conta;
	
	@JsonIgnore
	@Column(name="data_cadastro", nullable=false)
	private Timestamp dataCadastro;
	
	@Column(name="descricao", nullable=false, length=150)
	private String descricao;
	
	@Column(name="data_agendamento", nullable=false)
	private Date dataAgendamento;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Pagamento pagamento;

	@JsonIgnore
	private Boolean cancelado;
	
	public Agendamento() {
		this.dataCadastro = Util.getDataHoraAtual();
		this.setCancelado(Boolean.FALSE);
	}
	
	public Boolean isAgendamentoValido() {
		return dataAgendamento.compareTo(this.pagamento.getVencimento()) <= 0;
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Boolean getCancelado() {
		return cancelado;
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}	
}