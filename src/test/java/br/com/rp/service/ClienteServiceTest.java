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
	private final String ID_CLIENTE = "100";

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
	
	
	
	//Tede de atualização de dados
	@Test
	@UsingDataSet("db/cliente.xml")
	public void testaAtualizacaoDeDados() {
		Cliente cliente = new Cliente();
		Email email = new Email("douglas@test.com");
		Endereco endereco = new Endereco();
		endereco.setBairro("Jardim Longe");
		endereco.setCidade("Aoucarana");
		endereco.setEndereco("Rua presidente Roosevelt");
		endereco.setNumero("10");
		endereco.setUf("PR");
		Cpf cpf = new Cpf("13245678998");
		
		cliente.setId(101L);
		cliente.setEmail(email);
		cliente.setEndereco(endereco);
		cliente.setCpf(cpf);
		
		Cliente clienteBanco = this.clienteServiceImpl.buscarClientePorCpf(cpf);		
		
		Assert.assertEquals(clienteServiceImpl.atualizarDados(cliente), true);
		
		
		System.out.println("\n\n\n\n\n Douglas");
		System.out.println(clienteBanco.getNome());
		
		
		Assert.assertEquals(clienteBanco.getCpf().toString(), cpf.toString());
		
		
		
		
	}	
	//Tede de atualização de dados
	
	
	//Teste de Transferencia
	@Test
	public void testaTransferencia() {
		
		clienteServiceImpl.realizarTransferencia(ID_CLIENTE, contaOrigemId, contaDestinoId, valor)
		
	}
	//Teste de Transferencia
}
