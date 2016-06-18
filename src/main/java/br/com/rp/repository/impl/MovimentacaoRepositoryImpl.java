package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.repository.MovimentacaoResumoRepository;

@Stateless
public class MovimentacaoRepositoryImpl extends AbstractRepositoryImpl<MovimentacaoResumo> implements MovimentacaoResumoRepository {

	public MovimentacaoRepositoryImpl() {
		super(MovimentacaoResumo.class);
	}

}