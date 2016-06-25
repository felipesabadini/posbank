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
import br.com.rp.domain.Cartao;
import br.com.rp.domain.Despesa;
import br.com.rp.repository.CartaoRepository;
import br.com.rp.services.DespesaService;
import br.com.rp.services.exception.LimiteCartaoInsuficienteException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/despesa_delete.sql"})
public class DespesaServiceTest extends AbstractTest {
	
	private final static Long CARTAO_ID = 1000L;
	private final static int QUANTIDADE_REGISTROS = 5;
	
	@EJB
	private DespesaService despesaService;
	@EJB
	private CartaoRepository cartaoRepository;
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa_lista.xml"}) 
	public void testeA_consegueRetornarDespesasPorCartao(){
		List<Despesa> lstDespesa = despesaService.consultarDespesasPorCartaoId(CARTAO_ID);
		
		Assert.assertEquals(QUANTIDADE_REGISTROS, lstDespesa.size());
	}
	
	@Test(expected=LimiteCartaoInsuficienteException.class)
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml"}) 
	public void testeB_DeveLevantarExcessaoSemLimite(){
		Cartao cartao = cartaoRepository.findById(CARTAO_ID);
		cartao.setLimite(new BigDecimal("1000"));
		cartaoRepository.save(cartao);
		
		despesaService.registrarDespesa(CARTAO_ID, "Pagamento veiculo", new BigDecimal("200.00"));
		despesaService.registrarDespesa(CARTAO_ID, "Pagamento veiculo", new BigDecimal("200.00"));
		despesaService.registrarDespesa(CARTAO_ID, "Pagamento veiculo", new BigDecimal("200.00"));
		despesaService.registrarDespesa(CARTAO_ID, "Pagamento veiculo", new BigDecimal("200.00"));
		despesaService.registrarDespesa(CARTAO_ID, "Pagamento veiculo", new BigDecimal("200.00"));
		despesaService.registrarDespesa(CARTAO_ID, "Pagamento veiculo", new BigDecimal("200.00"));
	}

	@Test()
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/cartao.xml"}) 
	public void testeC_DeveRegistrarDespesa(){
		Cartao cartao = cartaoRepository.findById(CARTAO_ID);
		cartao.setLimite(new BigDecimal("1000"));
		cartaoRepository.save(cartao);
		
		despesaService.registrarDespesa(CARTAO_ID, "Pagamento veiculo", new BigDecimal("200.00"));
		despesaService.registrarDespesa(CARTAO_ID, "Pagamento veiculo", new BigDecimal("200.00"));
		despesaService.registrarDespesa(CARTAO_ID, "Pagamento veiculo", new BigDecimal("200.00"));
		
		List<Despesa> lstDespesa = despesaService.consultarDespesasPorCartaoId(CARTAO_ID);
		Assert.assertTrue(lstDespesa.size() == 3);
	}
}