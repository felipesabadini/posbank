package br.com.rp.service;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cargo;
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Endereco;
import br.com.rp.domain.Funcionario;
import br.com.rp.repository.FuncionarioRepository;
import br.com.rp.services.FuncionarioService;
import br.com.rp.services.exception.CPFDuplicadoException;

@CleanupUsingScript(value="db/funcionarioService_delete.sql", phase=TestExecutionPhase.AFTER)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioServiceTest extends AbstractTest {
	
	private static final Long FUNCIONARIO_ID = 1000L;
	
	@EJB
	FuncionarioService service;
	@EJB
	FuncionarioRepository repository;
	
	@Test
	public void testeA_deveinserirFuncionario() {
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
		
		service.salvarFuncionario(funcionario);
		Assert.assertNotNull(funcionario.getId());
	}
	
	@Test(expected=CPFDuplicadoException.class)
	@UsingDataSet("db/funcionario.xml")
	public void testeB_naoDeveInserirFuncionarioComCPFDuplicado() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Joao");
		funcionario.setCpf(new Cpf("78568965318"));
		Endereco endereco = new Endereco();
		endereco.setEndereco("Rua das Camélias");
		endereco.setBairro("Jardim");
		endereco.setCidade("Maringa");
		endereco.setNumero("123");
		endereco.setUf("PR");
		funcionario.setEndereco(endereco);
		funcionario.setCargo(Cargo.GERENTE);
		
		service.salvarFuncionario(funcionario);
	}
	
	@Test()
	@UsingDataSet("db/funcionario.xml")
	public void testeC_consegueAtualizarDadosFuncionarioSemLevantarErroCPFDuplicado() {
		Funcionario funcionario = repository.findById(FUNCIONARIO_ID);
		Assert.assertNotNull(funcionario);
		
		funcionario.setNome("Bruce Lee");
		
		service.salvarFuncionario(funcionario);
		
		Funcionario result = repository.findById(FUNCIONARIO_ID);
		Assert.assertEquals(result.getNome(), funcionario.getNome());
	}
}
