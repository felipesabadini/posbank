package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Funcionario;
import br.com.rp.repository.FuncionarioRepository;

@Stateless
public class FuncionarioRepositoryImpl extends AbstractRepositoryImpl<Funcionario> implements FuncionarioRepository {
	
	public FuncionarioRepositoryImpl() {
		super(Funcionario.class);
	}
	
}