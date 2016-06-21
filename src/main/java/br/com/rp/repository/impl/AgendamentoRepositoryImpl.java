package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.rp.domain.Agendamento;
import br.com.rp.repository.AgendamentoRepository;

@Stateless
public class AgendamentoRepositoryImpl extends AbstractRepositoryImpl<Agendamento> implements AgendamentoRepository {

	public AgendamentoRepositoryImpl() {
		super(Agendamento.class);
	}

	@Override
	public Agendamento encontrarAgendamentoAtivoPorID(Long id) {
		Query query = em.createQuery("from Agendamento a where a.id = :id and a.cancelado = false", Agendamento.class);
		query.setParameter("id", id);
		Object result = query.getSingleResult();
		if(result != null)
			return (Agendamento) result;
		else
			return null;
	}
	
}