package br.com.rp.service;

import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cargo;
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Endereco;
import br.com.rp.domain.Funcionario;
import br.com.rp.repository.FuncionarioRepository;

public class FuncionarioServiceTest extends AbstractTest {
	
	@EJB
	FuncionarioRepository funcionarioRepository;
	
	@Test
	public void deveinserirFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Douglas");
		
		Endereco endereco = new Endereco();
		endereco.setCidade("Apucarana");
		endereco.setBairro("Jardim vila alguma coisa");
		endereco.setNumero("10");
		endereco.setEndereco("Rua presidente {{nome}}");
		
		funcionario.setEndereco(endereco);
		funcionario.setCpf(new Cpf("07399125775"));
		funcionario.setCargo(Cargo.GERENTE);
		
		funcionarioRepository.save(funcionario);
		
		List<Funcionario> funcionarios = funcionarioRepository.getAll();
		
		Assert.assertTrue(funcionarios.size() > 0);
		
		
		
	}

}
