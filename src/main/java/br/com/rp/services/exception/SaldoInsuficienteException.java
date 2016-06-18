package br.com.rp.services.exception;

import javax.ejb.ApplicationException;

@SuppressWarnings("serial")
@ApplicationException(rollback=true)
public class SaldoInsuficienteException extends RuntimeException {
	
	public SaldoInsuficienteException() {
		super("Saldo insuficiente na conta");
	}
}