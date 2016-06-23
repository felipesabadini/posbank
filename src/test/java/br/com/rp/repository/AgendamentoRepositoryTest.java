package br.com.rp.repository;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Agendamento;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Pagamento;
import br.com.rp.domain.Tipo;
import br.com.rp.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/agendamento_delete.sql"})
public class AgendamentoRepositoryTest extends AbstractTest {
	
	private final Long AGENDAMENTO_TESTE_ID = 1000L;
	private final Long CONTA_TESTE_ID = 1000l;
	
	@EJB
	private AgendamentoRepository dao;
	@EJB
	private ContaRepository daoConta;
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml"})
	public void testeA_consegueInserirNoBanco(){
		Conta conta = daoConta.findById(CONTA_TESTE_ID);
		Assert.assertNotNull(conta);
		
		Pagamento pagamento = new Pagamento();
		pagamento.setValor(new BigDecimal("1000"));
		pagamento.setVencimento(Util.addData(3,Util.getDataAtual()));
		pagamento.setTipo(Tipo.BOLETO);
		pagamento.setConta(conta);
		
		Agendamento agendamento = new Agendamento();
		agendamento.setConta(conta);
		agendamento.setDataAgendamento(Util.addData(1, Util.getDataAtual()));
		agendamento.setDescricao("CONTA DE LUZ");
		agendamento.setPagamento(pagamento);
		
		dao.save(agendamento);
		
		Assert.assertNotNull(agendamento.getId());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/pagamento.xml",  "db/agendamento.xml"})
	public void testeB_consegueAtualizarRegistro(){
		Agendamento agendamento = dao.findById(AGENDAMENTO_TESTE_ID);
		Assert.assertNotNull(agendamento);	
		Date data = Util.addData(5, Util.getDataAtual());
		agendamento.setDataAgendamento(data);
		
		Agendamento result = dao.findById(AGENDAMENTO_TESTE_ID);
		
		Assert.assertEquals(data, result.getDataAgendamento());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/pagamento.xml", "db/agendamento.xml"})
	public void testeC_consegueDeletarRegistro(){
		dao.remove(AGENDAMENTO_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/pagamento.xml", "db/agendamento.xml"})
	public void testeD_consegueRecuperarRegistro(){
		Agendamento agendamento = dao.findById(AGENDAMENTO_TESTE_ID);
		Assert.assertNotNull(agendamento);
	}
}