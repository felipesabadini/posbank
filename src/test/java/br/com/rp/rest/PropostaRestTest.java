package br.com.rp.rest;

import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Email;
import br.com.rp.domain.Proposta;

public class PropostaRestTest extends AbstractTest {

	private static final String URL = "http://localhost:8080/vbank/api/propostas";
	
	@Test
	public void registrarUmaNovaProposta() {
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
}
