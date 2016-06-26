package br.com.rp.services.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class horarioInfornadoNaoPermitido extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public horarioInfornadoNaoPermitido(String mensagem) {
		super(mensagem);
	}	

}
