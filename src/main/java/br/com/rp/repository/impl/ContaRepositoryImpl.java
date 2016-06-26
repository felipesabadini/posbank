package br.com.rp.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.rp.domain.Conta;
import br.com.rp.repository.ContaRepository;

@Stateless
public class ContaRepositoryImpl extends AbstractRepositoryImpl<Conta> implements ContaRepository {

	public ContaRepositoryImpl() {
		super(Conta.class);
	}

	@SuppressWarnings("unchecked")
	public List<Conta> consultarContaPorClienteId(Long clienteId) {
		Query query = em.createQuery("from Conta c where c.cliente.id = :clienteId", Conta.class);
		query.setParameter("clienteId", clienteId);
		
		return query.getResultList();
	}

}