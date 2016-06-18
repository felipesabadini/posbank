package br.com.rp.repository;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Email;
import br.com.rp.domain.Endereco;

public class ClienteRepositoryTest extends AbstractTest {

	@EJB
	private ClienteRepository clienteRepository;
	
	@Test
	public void deveInserirUmNovoCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Joao");
		cliente.setCpf(new Cpf("12401661760"));
		cliente.setEmail(new Email("joao@joao.com.br"));
		Endereco endereco = new Endereco();
		endereco.setBairro("bairro");
		endereco.setCidade("cidade");
		endereco.setNumero("123");
		endereco.setUf("uf");
		cliente.setEndereco(endereco);
		
		clienteRepository.save(cliente);
		Assert.assertNotNull(cliente.getId());
	}
	
	@Test
	@UsingDataSet("db/cliente.xml")
	public void deveRemoverUmCliente() {
		this.clienteRepository.remove(100L);
		Cliente cliente = this.clienteRepository.findById(100L);
		Assert.assertNull(cliente);
	}
	
	@Test
	@UsingDataSet("db/cliente.xml")
	public void deveAtualizarONome() {
		Cliente cliente = this.clienteRepository.findById(100L);
		cliente.setNome("Joao");
		this.clienteRepository.save(cliente);
		
		Assert.assertEquals("Joao", cliente.getNome());
	}
}
