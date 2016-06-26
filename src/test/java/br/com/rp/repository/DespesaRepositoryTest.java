package br.com.rp.repository;

import java.math.BigDecimal;
import java.util.Calendar;

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
import br.com.rp.domain.Despesa;
import br.com.rp.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/despesa_delete.sql"})
public class DespesaRepositoryTest extends AbstractTest {

	private final static Long CARTAO_TESTE_ID = 1000L;
	
	private final static Long DESPESA_CARTAO_TESTE_ID = 1000L;
	
	private static BigDecimal VALOR_TOTAL = new BigDecimal("825.00");
	
	@EJB
	private DespesaRepository dao;
	
	@EJB
	private CartaoRepository cartaoRepository;
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa.xml"}) 
	public void testeA_deveInserirUmaNovaDespesa(){
		Cartao cartao = cartaoRepository.findById(CARTAO_TESTE_ID);
		
		Despesa despesa = new Despesa();
		despesa.setCartao(cartao);
		despesa.setDataLancamento(Util.getDataHoraAtual());
		despesa.setDescricao("Descricao da despesa");
		despesa.setValor(new BigDecimal("1000.00"));
		
		dao.save(despesa);
		Assert.assertNotNull(despesa.getId());
	}
	
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa.xml" })
	public void testeB_deveAtualizarUmaDespesa(){
		Despesa despesa = this.dao.findById(DESPESA_CARTAO_TESTE_ID);
		Assert.assertNotNull(despesa);
		
		despesa.setValor(new BigDecimal("1750.00"));
		
		dao.save(despesa);
		
		Despesa result = dao.findById(DESPESA_CARTAO_TESTE_ID);
		
		Assert.assertEquals(result.getValor().doubleValue(), despesa.getValor().doubleValue(), 0000001);
	}
	
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa.xml"})
	public void testeC_deveRemoverUmaDespesa(){
		dao.remove(DESPESA_CARTAO_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa.xml"})
	public void testeD_consegueRecuperarRegistro(){
		Despesa despesa = dao.findById(DESPESA_CARTAO_TESTE_ID);
		Assert.assertNotNull(despesa);
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa_lista.xml"}) 
	public void testeE_consegueRetornarTotalDespesaCartaoPorDataInformada(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.JUNE);
		calendar.set(Calendar.DATE, 13);
		
		BigDecimal valorTotal = dao.consultarTotalDespesaPorCartaoIdAPartirDataInformada(CARTAO_TESTE_ID, calendar.getTime());
		System.out.println("Valor total: " + valorTotal);
		
		Assert.assertTrue(valorTotal.compareTo(VALOR_TOTAL) == 0);
	}
}