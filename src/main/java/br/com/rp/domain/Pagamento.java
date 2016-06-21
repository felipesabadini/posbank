package br.com.rp.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Pagamento extends BaseEntity {

	private static final long serialVersionUID = 8141262331072373417L;

	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	private Date vencimento;
	
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
	
	
}
