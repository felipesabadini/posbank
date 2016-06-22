package br.com.rp.service;

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
import br.com.rp.domain.Despesa;
import br.com.rp.services.DespesaService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/despesa_delete.sql"})
public class DespesaServiceTest extends AbstractTest {
	
	private final static Long CARTAO_ID = 1000L;
	private final static int QUANTIDADE_REGISTROS = 5;
	
	@EJB
	private DespesaService despesaService;
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa_lista.xml"}) 
	public void testeA_consegueRetornarDespesasPorCartao(){
		List<Despesa> lstDespesa = despesaService.consultarDespesasPorCartaoId(CARTAO_ID);
		
		Assert.assertEquals(QUANTIDADE_REGISTROS, lstDespesa.size());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa_lista.xml"}) 
	public void testeA_consegueRetornarTotalDespesaCartaoPorDataInformada(){
		List<Despesa> lstDespesa = despesaService.consultarDespesasPorCartaoId(CARTAO_ID);
		
		Assert.assertEquals(QUANTIDADE_REGISTROS, lstDespesa.size());
	}
	
}