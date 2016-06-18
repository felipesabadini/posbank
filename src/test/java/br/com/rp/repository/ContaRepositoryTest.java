package br.com.rp.repository;

import java.math.BigDecimal;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Agencia;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Conta;
import br.com.rp.domain.TipoConta;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/conta_delete.sql"})
public class ContaRepositoryTest extends AbstractTest {
	
	private final Long AGENCIA_TESTE_ID = 1000l;
	
	private final Long CONTA_TESTE_ID = 1000L;
	
	private final Long CLIENTE_TESTE_ID = 100L;
	
	@EJB
	private ContaRepository dao;
	@EJB
	private AgenciaRepository daoAgencia;
	@EJB
	private ClienteRepository clienteRepository;
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml"})
	public void testeA_consegueInserirNoBanco(){
		Agencia agencia = daoAgencia.findById(AGENCIA_TESTE_ID);
		Cliente cliente = clienteRepository.findById(CLIENTE_TESTE_ID);
		
		
		Assert.assertNotNull(agencia);
		Assert.assertNotNull(cliente);
		
		Conta conta = new Conta();
		conta.setAgencia(agencia);
		conta.setLimite(new BigDecimal("1000.00"));
		conta.setNumero(12345);
		conta.setSaldo(new BigDecimal("150.00"));
		conta.setTipoConta(TipoConta.CC);
		conta.setCliente(cliente);
		
		dao.save(conta);	
		Assert.assertNotNull(agencia.getId());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/conta.xml"})
	public void testeB_consegueAtualizarRegistro(){
		Conta conta = dao.findById(CONTA_TESTE_ID);
		Assert.assertNotNull(conta);	
		conta.setNumero(98765);
		dao.save(conta);
		
		Conta result = dao.findById(CONTA_TESTE_ID);
		
		Assert.assertEquals(conta.getNumero(), result.getNumero());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/conta.xml"})
	public void testeC_consegueDeletarRegistro(){
		dao.remove(CONTA_TESTE_ID);
		Assert.assertEquals(1, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/conta.xml"})
	public void testeD_consegueRecuperarRegistro(){
		Conta conta = dao.findById(CONTA_TESTE_ID);
		Assert.assertNotNull(conta);
	}
}