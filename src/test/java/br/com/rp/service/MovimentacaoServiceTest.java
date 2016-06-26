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
import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.repository.ContaRepository;
import br.com.rp.services.MovimentacaoService;
import br.com.rp.services.exception.SaldoInsuficienteException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/movimentacaoService_delete.sql"})
public class MovimentacaoServiceTest extends AbstractTest {
	
	private static final Long CONTA_ORIGEM_ID = 1000L;
	private static final Long CONTA_DESTINO_ID = 1001L;
	private static final Integer QUANTIDADE_REGISTROS = 5;
	
	@EJB
	private MovimentacaoService service;
	
	@EJB 
	private ContaRepository contaRepository;
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao_lista.xml"})
	public void testeA_consegueRetornarListaMovimentacoesPorConta(){
		List<Movimentacao> lstMovimentacao = service.consultarMovimentacaoPorContaId(CONTA_ORIGEM_ID);
		
		Integer quantidadeRegistrosEncontrados = lstMovimentacao.size();
		Assert.assertEquals(quantidadeRegistrosEncontrados, QUANTIDADE_REGISTROS);
	}
	
	@Test(expected=SaldoInsuficienteException.class)
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao_lista.xml"})
	public void testeB_naoConsegueRealizarTransferenciaSemSaldo() throws SaldoInsuficienteException{
		service.realizarTransferenciaEntreContasVBank(CONTA_ORIGEM_ID, new BigDecimal("2000.00"), CONTA_DESTINO_ID);
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao_lista.xml"})
	public void testeC_deveConseguirRealizarTransferenciaEntreContasVBANK(){
		service.realizarTransferenciaEntreContasVBank(CONTA_ORIGEM_ID, new BigDecimal("1000.00"), CONTA_DESTINO_ID);
		
		Conta contaOrigem = contaRepository.findById(CONTA_ORIGEM_ID);
		Conta contaDestino = contaRepository.findById(CONTA_DESTINO_ID);
		
		Assert.assertEquals(contaOrigem.getSaldo().doubleValue(), BigDecimal.ZERO.doubleValue(), 000001);
		Assert.assertEquals(contaDestino.getSaldo(), new BigDecimal("2000.00"));
	}
}