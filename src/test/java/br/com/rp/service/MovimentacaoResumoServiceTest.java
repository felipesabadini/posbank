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
import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.services.ConfiguracaoService;
import br.com.rp.services.MovimentacaoResumoService;
import br.com.rp.services.MovimentacaoService;
import br.com.rp.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/movimentacaoService_delete.sql"})
public class MovimentacaoResumoServiceTest extends AbstractTest {
	
	private static final Long CONTA_ID = 1000L;
	
	private static final int QUANTIDADE_MOVIMENTACAO_RESUMO_NAO_ENVIADO_BACEN = 3;
	
	private static final int QUANTIDADE_MOVIMENTACAO_RESUMO_ENVIADO_BACEN = 2;
	
	private static final int QUANTIDADE_MOVIMENTACAO_RESUMO_ENVIADO_BACEN_NAO_ENVIADO_EUA = 1;
	
	@EJB
	private MovimentacaoResumoService service;
	
	@EJB
	private MovimentacaoService movimentacaoService;
	
	@EJB
	ConfiguracaoService configuracaoService;
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml"})
	public void testeA_consegueRegistarMovimentacaoResumoAoSalvarMovimentacao(){
		configuracaoService.definirHorarioTransacaoBancaria(Util.setTime(0, 0), Util.setTime(23, 59));
		
		movimentacaoService.realizarTransferencia(CONTA_ID, new BigDecimal("250.00"), "012-A", 1234567L);
		
		List<MovimentacaoResumo> lstMovimentacaoResumo = service.consultarMovimentacaoResumoNaoEnviadoBacen();
		Assert.assertTrue(lstMovimentacaoResumo.size() == 1);
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao_lista.xml", "db/movimentacaoResumo_lista.xml"})
	public void testeB_consegueRecuperarMovimentacaoResumoNaoEnviadaBacen(){
		List<MovimentacaoResumo> lstMovimentacaoResumo = service.consultarMovimentacaoResumoNaoEnviadoBacen();
		
		Assert.assertEquals(lstMovimentacaoResumo.size(), QUANTIDADE_MOVIMENTACAO_RESUMO_NAO_ENVIADO_BACEN);
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao_lista.xml", "db/movimentacaoResumo_lista.xml"})
	public void testeC_consegueRecuperarMovimentacaoResumoEnviadaBacen(){
		List<MovimentacaoResumo> lstMovimentacaoResumo = service.consultarMovimentacaoResumoEnviadoBacen();
		
		Assert.assertEquals(lstMovimentacaoResumo.size(), QUANTIDADE_MOVIMENTACAO_RESUMO_ENVIADO_BACEN);
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao_lista.xml", "db/movimentacaoResumo_lista.xml"})
	public void testeD_consegueRecuperarMovimentacaoResumoEnviadaBacenNaoEnviadoEUA(){
		List<MovimentacaoResumo> lstMovimentacaoResumo = service.consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA();
		
		Assert.assertEquals(lstMovimentacaoResumo.size(), QUANTIDADE_MOVIMENTACAO_RESUMO_ENVIADO_BACEN_NAO_ENVIADO_EUA);
	}
}