package br.com.rp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Agendamento extends  BaseEntity {
	
	private Timestamp dataCadastro;
	
	private BigDecimal valor;
	
	private String descricao;
	
	private Timestamp dataAgendamento;

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

	public Timestamp getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Timestamp dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}	
}