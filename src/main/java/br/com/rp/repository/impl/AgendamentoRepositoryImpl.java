package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Agendamento;
import br.com.rp.repository.AgendamentoRepository;

@Stateless
public class AgendamentoRepositoryImpl extends AbstractRepositoryImpl<Agendamento> implements AgendamentoRepository {

	public AgendamentoRepositoryImpl() {
		super(Agendamento.class);
	}
	
}