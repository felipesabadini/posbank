package br.com.rp.repository.impl;

import br.com.rp.domain.Agencia;
import br.com.rp.repository.AgenciaRepository;

public class AgenciaRepositoryImpl extends AbstractRepositoryImpl<Agencia> implements AgenciaRepository {

	public AgenciaRepositoryImpl() {
		super(Agencia.class);
	}
	
}