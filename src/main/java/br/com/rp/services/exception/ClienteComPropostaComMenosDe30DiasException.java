package br.com.rp.services.exception;

public class ClienteComPropostaComMenosDe30DiasException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ClienteComPropostaComMenosDe30DiasException(String mensagem) {
		super(mensagem);
	}	

}
