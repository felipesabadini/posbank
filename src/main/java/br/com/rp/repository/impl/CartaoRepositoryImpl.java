package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Cartao;
import br.com.rp.repository.CartaoRepository;

@Stateless
public class CartaoRepositoryImpl extends AbstractRepositoryImpl<Cartao> implements CartaoRepository {

	public CartaoRepositoryImpl() {
		super(Cartao.class);
	}
	
}