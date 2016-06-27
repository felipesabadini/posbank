package br.com.rp.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ConfiguracaoDTO  implements Serializable{
	
	private Date horaInicialTransacao;
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
