package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;

@Stateless
public class ConfiguracaoRepositoryImpl extends AbstractRepositoryImpl<Configuracao> implements ConfiguracaoRepository {

	public ConfiguracaoRepositoryImpl() {
		super(Configuracao.class);
	}
	
}