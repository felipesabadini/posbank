package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Proposta;
import br.com.rp.repository.PropostaRepository;

@Stateless
public class PropostaRepositoryImpl extends AbstractRepositoryImpl<Proposta> implements PropostaRepository {

	public PropostaRepositoryImpl() {
		super(Proposta.class);
	}



}
