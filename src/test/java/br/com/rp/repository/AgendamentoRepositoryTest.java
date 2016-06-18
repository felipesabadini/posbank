package br.com.rp.repository;

import java.math.BigDecimal;

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
		
		Agendamento agendamento = new Agendamento();
		agendamento.setConta(conta);
		agendamento.setDataAgendamento(Util.addData(1, Util.getDataAtual()));
		agendamento.setDataCadastro(Util.getDataHoraAtual());
		agendamento.setDescricao("CONTA DE LUZ");
		agendamento.setValor(new BigDecimal("1500.00"));
		
		dao.save(agendamento);
		
		Assert.assertNotNull(agendamento.getId());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/agendamento.xml"})
	public void testeB_consegueAtualizarRegistro(){
		Agendamento agendamento = dao.findById(AGENDAMENTO_TESTE_ID);
		Assert.assertNotNull(agendamento);	
		
		agendamento.setValor(new BigDecimal("1650.00"));
		
		Agendamento result = dao.findById(AGENDAMENTO_TESTE_ID);
		
		Assert.assertEquals(agendamento.getValor().doubleValue(), result.getValor().doubleValue(), 0000001);
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/agendamento.xml"})
	public void testeC_consegueDeletarRegistro(){
		dao.remove(AGENDAMENTO_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/cliente.xml", "db/conta.xml", "db/agendamento.xml"})
	public void testeD_consegueRecuperarRegistro(){
		Agendamento agendamento = dao.findById(AGENDAMENTO_TESTE_ID);
		Assert.assertNotNull(agendamento);
	}
}