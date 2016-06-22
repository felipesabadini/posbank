package br.com.rp.services.exception;

import javax.ejb.ApplicationException;

@SuppressWarnings("serial")
@ApplicationException(rollback=true)
public class LogMovimentacaoException extends RuntimeException {
	
	public LogMovimentacaoException(Exception e) {
		super("Não foi possivél registrar o Log da Movimentação", e.getCause());
	}
	
}