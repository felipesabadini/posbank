package br.com.rp.service;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.rp.services.MovimentacaoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/movimentacao_service_delete.sql"})
public class MovimentacaoServiceTest extends AbstractTest {
	
	private static final Long CONTA_ORIGEM_ID = 1000L;
	private static final Long CONTA_DESTINO_ID = 2000L;
	private static final Integer QUANTIDADE_REGISTROS = 5;
	
	@EJB
	private MovimentacaoService service;
	
	@Test
	@UsingDataSet("db/movimentacao_lista.xml")
	public void testeA_consegueRetornarListaMovimentacoesPorConta(){
		
		List<Movimentacao> lstMovimentacao = service.consultarMovimentacaoPorContaId(CONTA_ORIGEM_ID);
		
		Integer quantidadeRegistrosEncontrados = lstMovimentacao.size();
		Assert.assertEquals(quantidadeRegistrosEncontrados, QUANTIDADE_REGISTROS);
	}
	
	@Test
	@UsingDataSet("db/movimentacao_lista.xml")
	public void testeB_consegueRealizarTransferencia(){
		service.realizarTransferencia(CONTA_ORIGEM_ID, CONTA_DESTINO_ID, new BigDecimal("2000.00"));
	}
}