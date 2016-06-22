package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.FaturaCartao;
import br.com.rp.repository.FaturaCartaoRepository;

@Stateless
public class FaturaCartaoRepositoryImpl extends AbstractRepositoryImpl<FaturaCartao> implements FaturaCartaoRepository  {

	public FaturaCartaoRepositoryImpl() {
		super(FaturaCartao.class);
	}
	
}