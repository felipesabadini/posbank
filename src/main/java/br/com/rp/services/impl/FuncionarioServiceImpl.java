package br.com.rp.services.impl;

import javax.ejb.EJB;

import br.com.rp.domain.Funcionario;
import br.com.rp.repository.FuncionarioRepository;
import br.com.rp.services.FuncionarioService;

public class FuncionarioServiceImpl implements FuncionarioService {
	
	@EJB
	FuncionarioRepository funcionarioRepository;

	@Override
	public void inserirFuncionario(Funcionario funcionario) {
				
		funcionarioRepository.save(funcionario);
		
	}

}
