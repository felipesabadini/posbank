package br.com.rp.repository;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cargo;
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Funcionario;

public class FuncionarioRepositoryTest extends AbstractTest {
	
	@EJB
	private FuncionarioRepository dao;
	
	@Test
	public void deseInserirNoBanco(){
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("TESTE");
		funcionario.setCpf(new Cpf("06480549937"));
		funcionario.setCargo(Cargo.GERENTE);
		
		dao.save(funcionario);
		
		Assert.assertNotNull(funcionario.getId());
	}
	
}
