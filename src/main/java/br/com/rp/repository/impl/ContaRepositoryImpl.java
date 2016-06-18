package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Conta;
import br.com.rp.repository.ContaRepository;

@Stateless
public class ContaRepositoryImpl extends AbstractRepositoryImpl<Conta> implements ContaRepository {

	public ContaRepositoryImpl() {
		super(Conta.class);
	}

}