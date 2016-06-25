package br.com.rp.service;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cargo;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.SituacaoProposta;
import br.com.rp.repository.PropostaRepository;
import br.com.rp.services.GerenteService;

public class GerenteServiceTest extends AbstractTest{
	
	private static final long PROPOSTA_ID = 1003;

	@EJB
	PropostaRepository propostaRepository;
	
	@EJB
	GerenteService gerenteService;
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/funcionario.xml",
	"db/propostas.xml"})
	@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/deveRetornarListaDePropostaApenasSerForGerente.sql"})
	public void deveRetornarListaDePropostaApenasSerForGerente() {
		
		List<Proposta> propostas = gerenteService.visualizarPropostasPorEstado(Cargo.GERENTE, "PR");
		
		Assert.assertTrue(propostas.size() > 0);
		
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/funcionario.xml",
	"db/propostas.xml"})
	@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/deveAceitarProposta.sql"})
	public void deveAceitarProposta() {
		
		gerenteService.aceitarProposta(Cargo.GERENTE, PROPOSTA_ID);
		
		Proposta proposta = propostaRepository.findById(PROPOSTA_ID);
		
		Assert.assertEquals(proposta.getSituacao(), SituacaoProposta.AC);
	}

}
