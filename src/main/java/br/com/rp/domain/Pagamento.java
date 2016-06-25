package br.com.rp.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pagamento")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pagamento extends BaseEntity {

	private static final long serialVersionUID = 8141262331072373417L;

	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="conta_id")
	private Conta conta;
	
	private Date vencimento;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "linhaDigitavel")
	private String linhaDigitavel; 
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Date getVencimento() {
		return vencimento;
	}
	
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinhaDigitavel() {
		return linhaDigitavel;
	}

	public void setLinhaDigitavel(String linhaDigitavel) {
		this.linhaDigitavel = linhaDigitavel;
	}
	
	
}
