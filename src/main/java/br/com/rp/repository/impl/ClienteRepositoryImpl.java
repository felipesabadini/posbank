package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Cpf;
import br.com.rp.repository.ClienteRepository;

@Stateless
public class ClienteRepositoryImpl extends AbstractRepositoryImpl<Cliente> implements ClienteRepository {

	public ClienteRepositoryImpl() {
		super(Cliente.class);
	}

	@Override
	public Cliente findClienteByCpf(Cpf cpf) {
		
		Query query = em.createQuery("select c from Cliente c where c.cpf = :cpf", Cliente.class);
		query.setParameter("cpf", cpf);

		return (Cliente) query.getSingleResult();
	}
}
