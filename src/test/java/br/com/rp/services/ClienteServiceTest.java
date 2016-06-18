package br.com.rp.services;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Cpf;
import br.com.rp.repository.ClienteRepository;
import junit.framework.Assert;

public class ClienteServiceTest extends AbstractTest {

	
	@EJB
	private ClienteRepository clientereposiroy;
	
	
	
	@Test
	@UsingDataSet("db/cliente.xml")
	public void testaBuscaClientePorCpf() {
		
		Cliente clienteResult = this.clientereposiroy.findClienteByCpf(new Cpf("38803672303"));
		
		Assert.assertEquals("Cliente TESTE", clienteResult.getNome());
		
	}
	
	@Test
	@UsingDataSet("db/cliente.xml")
	public void testaCpfInesistente() {
		
		Cliente clienteResult = this.clientereposiroy.findClienteByCpf(new Cpf("05972184125"));
		
		Assert.assertNull(clienteResult);
		
	}
	
}
