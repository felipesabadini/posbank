package br.com.rp.rest;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Agendamento;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Pagamento;
import br.com.rp.domain.Tipo;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.PagamentoRepository;
import br.com.rp.seguranca.Token;
import br.com.rp.util.Util;

@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value = { "db/agendamento_delete.sql" })
public class AgendamentoRestTest extends AbstractTest {
	
	private static final String URL = "http://localhost:8080/vbank/api/agendamentos";
	
	
	@EJB
	private ContaRepository contaRepository;
	
	@EJB
	private PagamentoRepository pagamentoRepository;
	
	@Test
	@UsingDataSet({ 
		"db/cliente.xml", 
		"db/conta.xml"
		})
	public void deveRegistrarUmAgendamento() {
		Conta conta = this.contaRepository.findById(1000L);

		Date data = Util.getDataAtual();
		
		Pagamento pagamento = new Pagamento();
		pagamento.setValor(new BigDecimal("100.00"));
		pagamento.setVencimento(data);
		pagamento.setTipo(Tipo.BOLETO);
		pagamento.setConta(conta);
		
		Agendamento agendamento = new Agendamento();
		agendamento.setConta(conta);
		agendamento.setDataAgendamento(data);
		agendamento.setDescricao("Pagamento de boleto");
		agendamento.setPagamento(pagamento);

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL);

		Response response = target.request().header("token", Token.CLIENTE).post(Entity.json(agendamento));
		Assert.assertEquals(Integer.valueOf(201), Integer.valueOf(response.getStatus()));		
		Agendamento result = response.readEntity(Agendamento.class);
		Assert.assertNotNull(result.getId());		
	}
	
	@Test
	@UsingDataSet({  
		"db/cliente.xml", 
		"db/conta.xml", 
		"db/pagamento.xml",
		"db/agendamento.xml" })	
	public void deveCancelarUmAgendamento() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL);
		Long id = 1000L;
		Response response = target.request().header("token", Token.CLIENTE).put(Entity.json(id));
		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
	}
}
