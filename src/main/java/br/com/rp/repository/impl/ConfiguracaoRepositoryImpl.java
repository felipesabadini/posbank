package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;

@Stateless
public class ConfiguracaoRepositoryImpl extends AbstractRepositoryImpl<Configuracao> implements ConfiguracaoRepository {

	private static final long CONFIGURACAO_ID = 1L;

	public ConfiguracaoRepositoryImpl() {
		super(Configuracao.class);
	}
	
	@Override
	public Configuracao save(Configuracao configuracao){
		configuracao.setId(CONFIGURACAO_ID);
		return super.save(configuracao);
	}
}