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
import br.com.rp.domain.Conta;
import br.com.rp.domain.LogMovimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.TipoOperacao;
import br.com.rp.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/logmovimentacao_delete.sql"})
public class LogMovimentacaoRepositoryTest extends AbstractTest {
	
	private final static Long LOGMOVIMENTACAO_TESTE_ID = 1000L;
	private final static Long CONTA_TESTE_ID = 1000L;
	
	@EJB
	private LogMovimentacaoRepository dao;
	
	@EJB
	private ContaRepository contaDAO;
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml"})
	public void testeA_consegueInserirNoBanco(){
		Conta conta = contaDAO.findById(CONTA_TESTE_ID);
		Assert.assertNotNull(conta);
		
		LogMovimentacao logMovimentacao = new LogMovimentacao();
		logMovimentacao.setConta(conta);
		logMovimentacao.setDataCadastro(Util.getDataHoraAtual());
		logMovimentacao.setValor(new BigDecimal("2000.00"));
		logMovimentacao.setTipoOperacao(TipoOperacao.SAQUE);
		logMovimentacao.setTipoMovimentacao(TipoMovimentacao.DEBITO);
		
		dao.save(logMovimentacao);
		
		Assert.assertNotNull(logMovimentacao.getId());
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/logmovimentacao.xml"})
	public void testeB_consegueAtualizarRegistro(){
		LogMovimentacao logMovimentacao = dao.findById(LOGMOVIMENTACAO_TESTE_ID);
		Assert.assertNotNull(logMovimentacao);	
		logMovimentacao.setValor(new BigDecimal("1500.00"));
		
		dao.save(logMovimentacao);
		
		LogMovimentacao result = dao.findById(LOGMOVIMENTACAO_TESTE_ID);
		
		Assert.assertEquals(result.getValor().doubleValue(), logMovimentacao.getValor().doubleValue(), 0000001);
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/logmovimentacao.xml"})
	public void testeC_consegueDeletarRegistro(){
		dao.remove(LOGMOVIMENTACAO_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/logmovimentacao.xml"})
	public void testeD_consegueRecuperarRegistro(){
		LogMovimentacao logMovimentacao = dao.findById(LOGMOVIMENTACAO_TESTE_ID);
		Assert.assertNotNull(logMovimentacao);
	}
}