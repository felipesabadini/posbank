package br.com.rp.repository;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.MovimentacaoResumo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/movimentacaoResumo_delete.sql"})
public class MovimentacaoResumoRepositoryTest extends AbstractTest {
	
	private final Long MOVIMENTACAO_RESUMO_TESTE_ID = 1000L;

	private final Long MOVIMENTACAO_TESTE_ID = 1000L;
	
	@EJB
	private MovimentacaoResumoRepository dao;
	
	@EJB
	private MovimentacaoRepository movimentacaoDAO;
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/movimentacao.xml"})
	public void testeA_consegueInserirNoBanco(){
		Movimentacao movimentacao = movimentacaoDAO.findById(MOVIMENTACAO_TESTE_ID);
		Assert.assertNotNull(movimentacao);
		
		MovimentacaoResumo movimentacaoResumo = new MovimentacaoResumo();
		movimentacaoResumo.setMovimentacao(movimentacao);
		movimentacaoResumo.setEnviadoBacen(Boolean.FALSE);
		movimentacaoResumo.setEnviadoEUA(Boolean.FALSE);
		
		dao.save(movimentacaoResumo);
		
		Assert.assertNotNull(movimentacaoResumo.getId());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/movimentacao.xml", "db/movimentacao_resumo.xml"})
	public void testeB_consegueAtualizarRegistro(){
		MovimentacaoResumo movimentacaoResumo = dao.findById(MOVIMENTACAO_RESUMO_TESTE_ID);
		Assert.assertNotNull(movimentacaoResumo);	
		
		movimentacaoResumo.setEnviadoBacen(Boolean.TRUE);
		movimentacaoResumo.setEnviadoEUA(Boolean.TRUE);
		
		dao.save(movimentacaoResumo);
		
		MovimentacaoResumo result = dao.findById(MOVIMENTACAO_TESTE_ID);
		
		Assert.assertTrue((result.getEnviadoBacen() && movimentacaoResumo.getEnviadoBacen()));
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/movimentacao.xml", "db/movimentacao_resumo.xml"})
	public void testeC_consegueDeletarRegistro(){
		dao.remove(MOVIMENTACAO_RESUMO_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/movimentacao.xml", "db/movimentacao_resumo.xml"})
	public void testeD_consegueRecuperarRegistro(){
		MovimentacaoResumo movimentacaoResumo = dao.findById(MOVIMENTACAO_RESUMO_TESTE_ID);
		Assert.assertNotNull(movimentacaoResumo);
	}
}