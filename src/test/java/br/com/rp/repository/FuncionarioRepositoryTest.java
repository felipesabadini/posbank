package br.com.rp.repository;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cargo;
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Funcionario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioRepositoryTest extends AbstractTest {
	
	private final Long FUNCIONARIO_TESTE_ID = 1000l;
	
	@EJB
	private FuncionarioRepository dao;
	
	@Test
	public void testeA_consegueInserirNoBanco(){
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("TESTE");
		funcionario.setCpf(new Cpf("06480549937"));
		funcionario.setCargo(Cargo.GERENTE);
		
		dao.save(funcionario);
		
		Assert.assertNotNull(funcionario.getId());
	}
	
	@Test
	@UsingDataSet("db/funcionario.xml")
	public void testeB_consegueAtualizarRegistro(){
		Funcionario funcionario = dao.findById(FUNCIONARIO_TESTE_ID);
		Assert.assertNotNull(funcionario);
		
		funcionario.setNome("TESTE EDITAR");
		dao.save(funcionario);
		
		Funcionario result = dao.findById(FUNCIONARIO_TESTE_ID);
		
		Assert.assertEquals(funcionario.getNome(), result.getNome());
	}
	
	@Test
	@UsingDataSet("db/funcionario.xml")
	public void testeC_consegueDeletarRegistro(){
		dao.remove(FUNCIONARIO_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet("db/funcionario.xml")
	public void testeD_consegueRecuperarRegistro(){
		Funcionario funcionario = dao.findById(FUNCIONARIO_TESTE_ID);
		Assert.assertNotNull(funcionario);
	}
}