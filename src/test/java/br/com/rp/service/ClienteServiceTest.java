package br.com.rp.service;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Email;
import br.com.rp.domain.Endereco;
import br.com.rp.repository.ClienteRepository;
import br.com.rp.services.ClienteService;
import junit.framework.Assert;

public class ClienteServiceTest extends AbstractTest {
	
	private final String ID_CONTA_ORIGEN = "1000";
	private final String ID_CONTA_DESTINO = "1001";
	private final Long CONTA_ORIGEM_ID = 1000L;
	private final Long CONTA_DESTINO_ID = 1001L;
	
	@EJB
	private ClienteService clienteServiceImpl;
	@EJB
	private ClienteRepository clienteRepository;
	
	// Teste de busca por CPF
	@Test
	@UsingDataSet("db/cliente.xml")
	public void testaBuscaClientePorCpf() {
		
		Cliente clienteResult = this.clienteServiceImpl.buscarClientePorCpf(new Cpf("38803672303"));
		
		Assert.assertEquals("Cliente TESTE", clienteResult.getNome());
		
	}
	
	@Test
	public void testaCpfInesistente() {
		
		Assert.assertNull(this.clienteServiceImpl.buscarClientePorCpf(new Cpf("05972184125")));
		
	}
	// Teste de busca por CPF	
	
	
	
	//Teste de atualização de dados
	@Test
	@UsingDataSet("db/cliente.xml")
	public void testaAtualizacaoDeDados() {
		Email email = new Email("douglas@test.com");
		Endereco endereco = new Endereco();
		endereco.setBairro("Jardim Longe");
		endereco.setCidade("Aoucarana");
		endereco.setEndereco("Rua presidente Roosevelt");
		endereco.setNumero("10");
		endereco.setUf("PR");
		Cpf cpf = new Cpf("13245678998");
		
		Cliente clienteBanco = this.clienteServiceImpl.buscarClientePorCpf(cpf);
		
		clienteBanco.setEmail(email);
		clienteBanco.setEndereco(endereco);
		
		Assert.assertEquals(clienteServiceImpl.atualizarDados(clienteBanco), true);
		
		
		System.out.println("\n\n\n\n\n Douglas");
		System.out.println(clienteBanco.getNome());
		
		
		Assert.assertEquals(clienteBanco.getCpf().toString(), cpf.toString());
		
	}	
	//Teste de atualização de dados
}
