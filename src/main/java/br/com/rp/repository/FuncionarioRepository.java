package br.com.rp.repository;

import java.util.List;

import br.com.rp.domain.Cpf;
import br.com.rp.domain.Funcionario;

public interface FuncionarioRepository extends Repository<Funcionario> {
	
	public List<Funcionario> consultarFuncionarioPorCPF(Cpf cpf);
	
}	