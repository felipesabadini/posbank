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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Email;
import br.com.rp.domain.Funcionario;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.SituacaoProposta;
import br.com.rp.repository.ClienteRepository;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.FuncionarioRepository;
import br.com.rp.repository.PropostaRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(value = "db/propostaRest_delete.sql", phase = TestExecutionPhase.AFTER)
public class PropostaRestTest extends AbstractTest {

	private static final String URL = "http://localhost:8080/vbank/api/propostas";

	private static final Long PROPOSTA_ID = 1000L;

	private static final Long FUNCIONARIO_ID = 1000L;

	@EJB
	private ClienteRepository clienteRepository;
	@EJB
	private PropostaRepository propostaRepository;
	@EJB
	private ContaRepository contaRepository;
	@EJB
	private FuncionarioRepository funcionarioRepository;

	@Test
	public void testeA_consegueRegistrarUmaNovaPropostaParaClienteNaoCadastrado() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL);

		Cliente cliente = new Cliente();
		cliente.setCpf(new Cpf("77043980593"));
		cliente.setNome("Maria");
		cliente.setEmail(new Email("maria@maria.com.br"));

		Proposta proposta = new Proposta();
		proposta.setCliente(cliente);
		proposta.setMensagem("Quero muito ser cliente desse banco !");
		proposta.setRendimento(new BigDecimal("1500.55"));

		Response response = target.request().post(Entity.json(proposta));

		Assert.assertEquals(Integer.valueOf(201), Integer.valueOf(response.getStatus()));
		Proposta result = response.readEntity(Proposta.class);
		Assert.assertNotNull(result.getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	@UsingDataSet({ "db/cliente_lista.xml", "db/proposta_lista.xml" })
	public void testeB_consegueRecuperarPropostaRecebidaPorEstado() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/lista/PR");
		Response response = target.request().get();
		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
		List<Proposta> lstProposta = ((List<Proposta>) response.readEntity(List.class));
		Assert.assertEquals(4, lstProposta.size());
	}

	@Test
	@UsingDataSet({"db/cliente_lista.xml", "db/proposta_lista.xml"})
	public void testeC_consegueAceitarProposta() {
		Proposta proposta = propostaRepository.findById(PROPOSTA_ID);
		Assert.assertNotNull(proposta);
		Assert.assertEquals(proposta.getSituacao(), SituacaoProposta.REC);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/aceitar/" + proposta.getId());
		Response response = target.request().post(null);
		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
		
		proposta = propostaRepository.findById(PROPOSTA_ID);
		Assert.assertNotNull(proposta);
		
		List<Conta> lstConta = contaRepository.consultarContaPorClienteId(proposta.getCliente().getId());
		Assert.assertTrue(lstConta != null && !lstConta.isEmpty());
	}

	@Test
	@UsingDataSet({"db/funcionario.xml", "db/cliente_lista.xml", "db/proposta_lista.xml" })
	public void testeD_consegueRejeitarProposta() {
		Funcionario funcionario = funcionarioRepository.findById(FUNCIONARIO_ID);
		
		Proposta proposta = new Proposta();
		proposta.setId(PROPOSTA_ID);
		proposta.setMotivoRejeicao("Infelizmente nossa grupo financeiro encontrou irregularidades em seu nome.");
		proposta.setFuncionario(funcionario);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/rejeitar");
		Response response = target.request().post(Entity.json(proposta));
		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
		
		proposta = propostaRepository.findById(PROPOSTA_ID);
		Assert.assertNotNull(proposta);
		Assert.assertEquals(proposta.getSituacao(), SituacaoProposta.REG);
	}
}