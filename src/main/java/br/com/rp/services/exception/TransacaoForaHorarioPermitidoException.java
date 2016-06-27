package br.com.rp.services.exception;

import java.util.Date;

import javax.ejb.ApplicationException;

import br.com.rp.util.Util;

@ApplicationException
public class TransacaoForaHorarioPermitidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TransacaoForaHorarioPermitidoException(Date horaInicialTransacao, Date horaFinalTransacao) {
		super("A transação só pode ser realizada entre " + Util.formataData(horaInicialTransacao, "HH:mm") + " e " + Util.formataData(horaFinalTransacao, "HH:mm"));
	}	

}
