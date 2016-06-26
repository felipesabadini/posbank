package br.com.rp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="configuracao")
public class Configuracao extends BaseEntity {
	
	@Temporal(TemporalType.TIME)
	@Column(name="hora_inicial_transacao")
	private Date horaInicialTransacao;
	
	@Temporal(TemporalType.TIME)
	@Column(name="hora_final_transacao")
	private Date horaFinalTransacao;

	public Date getHoraInicialTransacao() {
		return horaInicialTransacao;
	}

	public void setHoraInicialTransacao(Date horaInicialTransacao) {
		this.horaInicialTransacao = horaInicialTransacao;
	}

	public Date getHoraFinalTransacao() {
		return horaFinalTransacao;
	}

	public void setHoraFinalTransacao(Date horaFinalTransacao) {
		this.horaFinalTransacao = horaFinalTransacao;
	}
}