package br.com.rp.service;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.repository.MovimentacaoResumoRepository;
import br.com.rp.services.MovimentacaoResumoService;

public class IntegradorServiceTest extends AbstractTest {
	
	private final static Long MOVIMENTACAO_RESUMO_ID = 1000L;

	@EJB
	private MovimentacaoResumoService service;
	
	@EJB
	private MovimentacaoResumoRepository repository;
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao.xml", "db/movimentacao_resumo.xml"})
	public void testeA_consegueEnviarMovimentacaoResumoParaBACEN(){
		service.enviarFilaBacen();
		
		MovimentacaoResumo movimentacaoResumo = repository.findById(MOVIMENTACAO_RESUMO_ID);
		Assert.assertTrue(movimentacaoResumo.getEnviadoBacen());
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao.xml", "db/movimentacao_resumo.xml"})
	public void testeB_consegueEnviarMovimentacaoResumoParaEUA() {
		MovimentacaoResumo movimentacaoResumo = repository.findById(MOVIMENTACAO_RESUMO_ID);
		movimentacaoResumo.setEnviadoBacen(Boolean.TRUE);
		repository.save(movimentacaoResumo);
		
		service.enviarFilaEUA();
		
		movimentacaoResumo = repository.findById(MOVIMENTACAO_RESUMO_ID);
		Assert.assertTrue(movimentacaoResumo.getEnviadoEUA());
	}
}