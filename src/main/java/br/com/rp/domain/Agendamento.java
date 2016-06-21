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
@Table(name="agendamento")
public class Agendamento extends  BaseEntity {
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="conta_id", nullable=false)
	private Conta conta;
	
	@Column(name="data_cadastro", nullable=false)
	private Timestamp dataCadastro;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@Column(name="descricao", nullable=false, length=100)
	private String descricao;
	
	@Column(name="data_agendamento", nullable=false)
	private Date dataAgendamento;
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
}