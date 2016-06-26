package br.com.rp.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="movimentacao_resumo")
public class MovimentacaoResumo extends BaseEntity implements Serializable {
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="movimentacao_id", nullable=false)
	private Movimentacao movimentacao;

	@Column(name="enviado_bacen", nullable=false)
	private Boolean enviadoBacen;
	
	@Column(name="enviado_eua")
	private Boolean enviadoEUA;
	
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}
	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	public Boolean getEnviadoBacen() {
		return enviadoBacen;
	}
	public void setEnviadoBacen(Boolean enviadoBacen) {
		this.enviadoBacen = enviadoBacen;
	}
	public Boolean getEnviadoEUA() {
		return enviadoEUA;
	}
	public void setEnviadoEUA(Boolean enviadoEUA) {
		this.enviadoEUA = enviadoEUA;
	}
}