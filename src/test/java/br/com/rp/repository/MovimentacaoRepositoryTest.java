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
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.TipoOperacao;
import br.com.rp.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/movimentacao_delete.sql"})
public class MovimentacaoRepositoryTest extends AbstractTest {
	
	private final Long MOVIMENTACAO_TESTE_ID = 1000L;

	private final Long CONTA_TESTE_ID = 1000L;
	
	@EJB
	private MovimentacaoRepository dao;
	
	@EJB
	private ContaRepository contaDAO;
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml"})
	public void testeA_consegueInserirNoBanco(){
		Conta conta = contaDAO.findById(CONTA_TESTE_ID);
		Assert.assertNotNull(conta);
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setDataMovimentacao(Util.getDataHoraAtual());
		movimentacao.setTipoMovimentacao(TipoMovimentacao.CREDITO);
		movimentacao.setTipoOperacao(TipoOperacao.TRANSFERENCIA);
		movimentacao.setValor(new BigDecimal("2500.00"));
		
		dao.save(movimentacao);
		
		Assert.assertNotNull(movimentacao.getId());
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao.xml"})
	public void testeB_consegueAtualizarRegistro(){
		Movimentacao movimentacao = dao.findById(MOVIMENTACAO_TESTE_ID);
		Assert.assertNotNull(movimentacao);	
		movimentacao.setValor(new BigDecimal("1500.00"));
		
		dao.save(movimentacao);
		
		Movimentacao result = dao.findById(MOVIMENTACAO_TESTE_ID);
		
		Assert.assertEquals(result.getValor().doubleValue(), movimentacao.getValor().doubleValue(), 0000001);
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao.xml"})
	public void testeC_consegueDeletarRegistro(){
		dao.remove(MOVIMENTACAO_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao.xml"})
	public void testeD_consegueRecuperarRegistro(){
		Movimentacao movimentacao = dao.findById(MOVIMENTACAO_TESTE_ID);
		Assert.assertNotNull(movimentacao);
	}
}