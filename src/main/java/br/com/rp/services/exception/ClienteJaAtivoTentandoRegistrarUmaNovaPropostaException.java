package br.com.rp.services.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException extends RuntimeException {

	private static final long serialVersionUID = -7364645311432886021L;

	public ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException(String mensagem) {
		super(mensagem);
	}

}
