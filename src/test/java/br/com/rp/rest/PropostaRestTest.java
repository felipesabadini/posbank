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
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Email;
import br.com.rp.domain.Proposta;
import br.com.rp.repository.ClienteRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(value="db/propostaRest_delete.sql", phase=TestExecutionPhase.AFTER)
public class PropostaRestTest extends AbstractTest {

	private static final String URL = "http://localhost:8080/vbank/api/propostas";
	
	@EJB
	private ClienteRepository clienteRepository;
	
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
	@UsingDataSet({"db/cliente_lista.xml", "db/proposta_lista.xml"})
	public void testeB_consegueRecuperarPropostaRecebidaPorEstado() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL + "/lista/PR");
		Response response = target.request().get();
		Assert.assertEquals(Integer.valueOf(200), Integer.valueOf(response.getStatus()));
		List<Proposta> lstProposta = ((List<Proposta>) response.readEntity(List.class));
		Assert.assertEquals(4, lstProposta.size());
	}
}