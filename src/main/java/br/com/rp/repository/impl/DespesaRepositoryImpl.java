package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Despesa;
import br.com.rp.domain.Proposta;
import br.com.rp.repository.DespesaRepository;
import br.com.rp.repository.PropostaRepository;

@Stateless
public class DespesaRepositoryImpl extends AbstractRepositoryImpl<Despesa> implements DespesaRepository {

	public DespesaRepositoryImpl() {
		super(Despesa.class);
	}

}
