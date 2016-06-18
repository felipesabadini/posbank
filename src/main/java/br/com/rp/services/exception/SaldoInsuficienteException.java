package br.com.rp.services.exception;

@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {
	
	public SaldoInsuficienteException() {
		super("Saldo insuficiente na conta");
	}
}