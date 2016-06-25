package br.com.rp.services.exception;

import javax.ejb.ApplicationException;

@SuppressWarnings("serial")
@ApplicationException(rollback=true)
public class CPFDuplicadoException extends RuntimeException {
	
	public CPFDuplicadoException(String message) {
		super(message);
	}
	
}
