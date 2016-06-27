package br.com.rp.services.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NivelDeAcessoIncompativel extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NivelDeAcessoIncompativel(String mensagem) {
		super(mensagem);
	}	

}