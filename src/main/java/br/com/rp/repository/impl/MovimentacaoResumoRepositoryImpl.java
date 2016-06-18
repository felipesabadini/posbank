package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.repository.MovimentacaoResumoRepository;

@Stateless
public class MovimentacaoResumoRepositoryImpl extends AbstractRepositoryImpl<MovimentacaoResumo> implements MovimentacaoResumoRepository {

	public MovimentacaoResumoRepositoryImpl() {
		super(MovimentacaoResumo.class);
	}
	
}