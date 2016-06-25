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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import br.com.rp.util.Util;

@SuppressWarnings("serial")
@Entity
@Table(name="agendamento")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Agendamento extends  BaseEntity {
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="conta_id")
	private Conta conta;
	
	@JsonIgnore
	@Column(name="data_cadastro", nullable=false)
	private Timestamp dataCadastro;
	
	@Column(name="descricao", nullable=false, length=150)
	private String descricao;
	
	@Column(name="data_agendamento", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataAgendamento;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Pagamento pagamento;

	@JsonIgnore
	@Column(name = "cancelado")
	private Boolean cancelado;

	@Column(name = "pago")
	private Boolean pago;
	
	public Agendamento() {
		this.dataCadastro = Util.getDataHoraAtual();
		this.setCancelado(Boolean.FALSE);
		this.setPago(Boolean.FALSE);
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

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}	
}