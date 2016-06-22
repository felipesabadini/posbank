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
import br.com.rp.domain.Cartao;
import br.com.rp.domain.Conta;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/cartao_delete.sql"})
public class CartaoRepositoryTest extends AbstractTest {
	
	private final Long CONTA_TESTE_ID = 1000L;
	
	private final Long CARTAO_TESTE_ID = 1000L;
	
	@EJB
	private CartaoRepository dao;
	@EJB
	private ContaRepository contaDao;
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml"})
	public void testeA_consegueInserirNoBanco(){
		Conta conta = contaDao.findById(CONTA_TESTE_ID);
		Assert.assertNotNull(conta);
		
		Cartao cartao = new Cartao();
		cartao.setConta(conta);
		cartao.setDiaVencimento(15);
		cartao.setLimite(new BigDecimal("1000.00"));
		
		dao.save(cartao);	
		Assert.assertNotNull(cartao.getId());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml"})
	public void testeB_consegueAtualizarRegistro(){
		Cartao cartao = dao.findById(CONTA_TESTE_ID);
		Assert.assertNotNull(cartao);	
		cartao.setLimite(new BigDecimal("1500.00"));
		dao.save(cartao);
		
		Cartao result = dao.findById(CARTAO_TESTE_ID);
		
		Assert.assertEquals(cartao.getLimite().doubleValue(), result.getLimite().doubleValue(), 000001);
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml"})
	public void testeC_consegueDeletarRegistro(){
		dao.remove(CONTA_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml"})
	public void testeD_consegueRecuperarRegistro(){
		Cartao cartao = dao.findById(CARTAO_TESTE_ID);
		Assert.assertNotNull(cartao);
	}
}