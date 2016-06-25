package br.com.rp.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Funcionario;
import br.com.rp.repository.FuncionarioRepository;
import br.com.rp.services.FuncionarioService;
import br.com.rp.services.exception.CPFDuplicadoException;

@Stateless
public class FuncionarioServiceImpl implements FuncionarioService {
	
	@EJB
	FuncionarioRepository funcionarioRepository;

	public void salvarFuncionario(Funcionario funcionario) {
		verificarExisteFuncionarioJaCadastradoComCPFInformado(funcionario);
		funcionarioRepository.save(funcionario);
	}
	
	private void verificarExisteFuncionarioJaCadastradoComCPFInformado(Funcionario funcionario){
		List<Funcionario> lstFuncionario = funcionarioRepository.consultarFuncionarioPorCPF(funcionario.getCpf());
		if (lstFuncionario != null && !lstFuncionario.isEmpty()){
			Funcionario result = lstFuncionario.get(0);
			if (!result.getId().equals(funcionario.getId())){
				throw new CPFDuplicadoException("O funcionário " + funcionario.getNome() + " está associao a este CPF.");
			}
		}
	}
}