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
import br.com.rp.domain.Conta;
import br.com.rp.domain.TipoConta;
import br.com.rp.repository.impl.ContaRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/conta_delete.sql"})
public class ContaRepositoryTest extends AbstractTest {
	
	private final Long AGENCIA_TESTE_ID = 1000l;
	
	@EJB
	private ContaRepository dao;
	@EJB
	private AgenciaRepository daoAgencia;
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml"})
	public void testeA_consegueInserirNoBanco(){
		Agencia agencia = daoAgencia.findById(AGENCIA_TESTE_ID);
		Assert.assertNotNull(agencia);
		
		Conta conta = new Conta();
		conta.setAgencia(agencia);
		conta.setLimite(new BigDecimal("1000.00"));
		conta.setNumero(12345);
		conta.setSaldo(new BigDecimal("150.00"));
		conta.setTipoConta(TipoConta.CONTA_CORRENTE);
		
		dao.save(conta);	
		Assert.assertNotNull(agencia.getId());
	}
	
	/*@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml"})
	public void testeB_consegueAtualizarRegistro(){
		Agencia agencia = dao.findById(AGENCIA_TESTE_ID);
		Assert.assertNotNull(agencia);	
		agencia.setNome("12345");
		dao.save(agencia);
		
		Agencia result = dao.findById(AGENCIA_TESTE_ID);
		
		Assert.assertEquals(agencia.getNome(), result.getNome());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml"})
	public void testeC_consegueDeletarRegistro(){
		dao.remove(AGENCIA_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml"})
	public void testeD_consegueRecuperarRegistro(){
		Agencia agencia = dao.findById(AGENCIA_TESTE_ID);
		Assert.assertNotNull(agencia);
	}*/
}