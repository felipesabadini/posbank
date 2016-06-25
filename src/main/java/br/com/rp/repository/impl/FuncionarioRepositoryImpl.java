package br.com.rp.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.rp.domain.Cpf;
import br.com.rp.domain.Funcionario;
import br.com.rp.repository.FuncionarioRepository;

@Stateless
public class FuncionarioRepositoryImpl extends AbstractRepositoryImpl<Funcionario> implements FuncionarioRepository {
	
	public FuncionarioRepositoryImpl() {
		super(Funcionario.class);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> consultarFuncionarioPorCPF(Cpf cpf) {
		Query query = em.createQuery("from Funcionario f where f.cpf = :cpf", Funcionario.class);
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}
	
}