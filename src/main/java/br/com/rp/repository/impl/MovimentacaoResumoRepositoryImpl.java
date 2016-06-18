package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Agencia;
import br.com.rp.repository.AgenciaRepository;

@Stateless
public class MovimentacaoResumoRepositoryImpl extends AbstractRepositoryImpl<Agencia> implements AgenciaRepository {

	public MovimentacaoResumoRepositoryImpl() {
		super(Agencia.class);
	}
	
}