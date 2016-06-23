package br.com.rp.repository;

import java.math.BigDecimal;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Pagamento;
import br.com.rp.domain.Tipo;
import br.com.rp.util.Util;

@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/conta_delete.sql"})
public class PagamentoRepositoryTest extends AbstractTest {

	@EJB
	private PagamentoRepository pagamentoRepository;
	
	@EJB
	private ContaRepository contaRepository;	
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml"})
	public void deveSalvarUmPagamento() {
		Conta conta = this.contaRepository.findById(1000L);
		Pagamento pagamento = new Pagamento();
		pagamento.setTipo(Tipo.BOLETO);
		pagamento.setValor(new BigDecimal("100.00"));
		pagamento.setVencimento(Util.getDataAtual());
		pagamento.setConta(conta);
		
		this.pagamentoRepository.save(pagamento);
		
		Assert.assertNotNull(pagamento.getId());
	}
}
