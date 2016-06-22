package br.com.rp.services.exception;

import javax.ejb.ApplicationException;

@SuppressWarnings("serial")
@ApplicationException(rollback=true)
public class LimiteCartaoInsuficienteException extends RuntimeException {
	
	public LimiteCartaoInsuficienteException() {
		super("Limite insuficiente no Cartão de Crédito");
	}
}