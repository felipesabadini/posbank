package br.com.rp.service;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Movimentacao;
import br.com.rp.services.MovimentacaoService;

public class MovimentacaoServiceTest extends AbstractTest {
	
	private static final Long CONTA_ID = 1000L;
	private static final Integer QUANTIDADE_REGISTROS = 5;
	
	@EJB
	private MovimentacaoService service;
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/conta.xml", "db/movimentacao_lista.xml"})
	public void consegueRetornarListaMovimentacoesPorConta(){
		
		List<Movimentacao> lstMovimentacao = service.consultarMovimentacaoPorContaId(CONTA_ID);
		
		Integer quantidadeRegistrosEncontrados = lstMovimentacao.size();
		Assert.assertEquals(quantidadeRegistrosEncontrados, QUANTIDADE_REGISTROS);
	}
	
	@Test
	public void consegueRealizarTransferencia(){
		
	}
}