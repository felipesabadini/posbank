package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Conta;

@Stateless
public class ContaRepositoryImpl extends AbstractRepositoryImpl<Conta> implements ContaRepository {

	public ContaRepositoryImpl() {
		super(Conta.class);
	}

}