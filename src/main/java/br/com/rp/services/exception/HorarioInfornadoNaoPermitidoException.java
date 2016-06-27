package br.com.rp.services.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class HorarioInfornadoNaoPermitidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public HorarioInfornadoNaoPermitidoException(String mensagem) {
		super(mensagem);
	}	

}
