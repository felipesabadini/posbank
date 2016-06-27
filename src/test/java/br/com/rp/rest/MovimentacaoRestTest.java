package br.com.rp.rest;

import java.math.BigDecimal;
import java.util.List;

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
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Despesa;
import br.com.rp.dto.MovimentacaoDTO;
import br.com.rp.repository.CartaoRepository;
import br.com.rp.repository.DespesaRepository;
import br.com.rp.seguranca.Token;
import br.com.rp.services.ConfiguracaoService;
import br.com.rp.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(value = "db/movimentacaoRest_delete.sql", phase = TestExecutionPhase.AFTER)
public class MovimentacaoRestTest extends AbstractTest {

	private static final String URL = "http://localhost:8080/vbank/api/movimentacoes";
	private static final Long CONTA_ID = 1000L;
	private static final Long CONTA_DESTINO_ID = 1001L;
	private static final Long PAGAMENTO_ID = 1000L;
	private static final Long CARTAO_ID = 1000L;
	
	@EJB
	CartaoRepository cartaoRepository;
	
	@EJB
	DespesaRepository despesaRepository;

	@EJB
	ConfiguracaoService configuracaoService;
	
	@Before
	public void init() {
		configuracaoService.definirHorarioTransacaoBancaria(Util.setTime(0, 0), Util.setTime(23, 59));
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml"})
	public void testeA_consegueRealizarTransferenciaEntreContasVBank() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/transferenciaVBank");
		
		MovimentacaoDTO movimentacao = new MovimentacaoDTO();
		movimentacao.setContaId(CONTA_ID);
		movimentacao.setValor(new BigDecimal("250.00"));
		movimentacao.setContaDestinoId(CONTA_DESTINO_ID);
		
		Response response = target.request().header("token", Token.CLIENTE).post(Entity.json(movimentacao));

		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml"})
	public void testeB_consegueRealizarTransferenciaParaOutrosBancos() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/transferencia");
		
		MovimentacaoDTO movimentacao = new MovimentacaoDTO();
		movimentacao.setContaId(CONTA_ID);
		movimentacao.setValor(new BigDecimal("250.00"));
		movimentacao.setContaDestinoId(CONTA_DESTINO_ID);
		
		Response response = target.request().header("token", Token.CLIENTE).post(Entity.json(movimentacao));

		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/pagamento.xml"})
	public void testeC_consegueRealizarPagamento() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/pagamento");
		
		MovimentacaoDTO movimentacao = new MovimentacaoDTO();
		movimentacao.setContaId(CONTA_ID);
		movimentacao.setValor(new BigDecimal("250.00"));
		movimentacao.setPagamentoId(PAGAMENTO_ID);
		
		Response response = target.request().header("token", Token.CLIENTE).post(Entity.json(movimentacao));

		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/pagamento.xml"})
	public void testeD_consegueRealizarDeposito() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/deposito");
		
		MovimentacaoDTO movimentacao = new MovimentacaoDTO();
		movimentacao.setContaId(CONTA_ID);
		movimentacao.setValor(new BigDecimal("1000000.00"));
		movimentacao.setCmc7("443513521717748775153217845691");
		
		Response response = target.request().header("token", Token.CLIENTE).post(Entity.json(movimentacao));

		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/cartao.xml", "db/despesa_lista.xml"})
	public void testeE_consegueConsultarDespesasPorCartao() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/despesas/" + CARTAO_ID);
		Response response = target.request().header("token", Token.CLIENTE).get();
		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
		List<Despesa> lstDespesa = ((List<Despesa>) response.readEntity(List.class));
		Assert.assertEquals(5, lstDespesa.size());
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/cartao.xml", "db/movimentacao_lista.xml"})
	public void testeF_consegueConsultarMovimentacaoPorConta() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/movimentacoes/" + CONTA_ID);
		Response response = target.request().header("token", Token.CLIENTE).get();
		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
		List<Conta> lstConta = ((List<Conta>) response.readEntity(List.class));
		Assert.assertEquals(5, lstConta.size());
	}
}