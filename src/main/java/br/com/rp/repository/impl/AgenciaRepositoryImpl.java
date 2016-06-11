package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Agencia;
import br.com.rp.repository.AgenciaRepository;

@Stateless
public class AgenciaRepositoryImpl extends AbstractRepositoryImpl<Agencia> implements AgenciaRepository {

	public AgenciaRepositoryImpl() {
		super(Agencia.class);
	}
	
}