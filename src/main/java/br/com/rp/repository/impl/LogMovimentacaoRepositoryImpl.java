package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.LogMovimentacao;
import br.com.rp.repository.LogMovimentacaoRepository;

@Stateless
public class LogMovimentacaoRepositoryImpl extends AbstractRepositoryImpl<LogMovimentacao> implements LogMovimentacaoRepository {

	public LogMovimentacaoRepositoryImpl() {
		super(LogMovimentacao.class);
	}
	
}