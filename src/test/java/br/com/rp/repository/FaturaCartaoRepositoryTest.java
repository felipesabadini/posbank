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
import br.com.rp.domain.FaturaCartao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/faturaCartao_delete.sql"})
public class FaturaCartaoRepositoryTest extends AbstractTest {

	private final static Long FATURA_CARTAO_ID = 1000L;
	
	private final static Long CARTAO_ID = 1000L;
	
	@EJB
	private FaturaCartaoRepository dao;
	
	@EJB
	private CartaoRepository cartaoRepository;
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml"}) 
	public void testeA_deveInserirUmaNovaDespesa(){
		Cartao cartao = cartaoRepository.findById(CARTAO_ID);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, cartao.getDiaVencimento());
		
		FaturaCartao faturaCartao = new FaturaCartao();
		faturaCartao.setCartao(cartao);
		faturaCartao.setValor(new BigDecimal("1500.00"));
		faturaCartao.setDataVencimento(calendar.getTime());
		faturaCartao.setMesReferencia(calendar.get(Calendar.MONTH));
		faturaCartao.setAnoReferencia(calendar.get(Calendar.YEAR));
		
		dao.save(faturaCartao);
		Assert.assertNotNull(faturaCartao.getId());
	}
	
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/faturaCartao.xml"})
	public void testeB_deveAtualizarUmRegistro(){
		FaturaCartao faturaCartao = dao.findById(FATURA_CARTAO_ID);
		Assert.assertNotNull(faturaCartao);
		
		faturaCartao.setValor(new BigDecimal("1750.00"));
		
		dao.save(faturaCartao);
		
		FaturaCartao result = dao.findById(FATURA_CARTAO_ID);
		
		Assert.assertEquals(result.getValor().doubleValue(), faturaCartao.getValor().doubleValue(), 0000001);
	}
	
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/faturaCartao.xml"})
	public void testeC_deveRemoverUmRegistro(){
		dao.remove(FATURA_CARTAO_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/faturaCartao.xml"})
	public void testeD_consegueRecuperarRegistro(){
		FaturaCartao faturaCartao = dao.findById(FATURA_CARTAO_ID);
		Assert.assertNotNull(faturaCartao);
	}
}