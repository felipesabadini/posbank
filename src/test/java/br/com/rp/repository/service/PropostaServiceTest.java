package br.com.rp.repository.service;

import javax.ejb.EJB;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.services.PropostaService;
import br.com.rp.services.exception.ClienteComPropostaComMenosDe30DiasException;


public class PropostaServiceTest extends AbstractTest {

	@EJB
	private PropostaService propostaService;
//	@EJB
//	private ClienteRepository clienteRepository;		
	
	@Test(expected = ClienteComPropostaComMenosDe30DiasException.class)
//	@UsingDataSet(value = {"db/cliente.xml", "db/proposta.xml"})
//	@Test
	public void deveLancarUmExceptionCasoJaExistaPropostaParaOClienteComMenosDe30Dias() {

			propostaService.oClienteTemPropostaComMenosDe30Dias(new Cliente());	

	}
}
