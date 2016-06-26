package br.com.rp.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cargo;
import br.com.rp.domain.Configuracao;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.SituacaoProposta;
import br.com.rp.repository.ConfiguracaoRepository;
import br.com.rp.repository.PropostaRepository;
import br.com.rp.services.GerenteService;
import br.com.rp.util.Util;

public class GerenteServiceTest extends AbstractTest{
	
	private static final long CONFIGURACAO_ID = 1L;

	private static final String MOTIVO_REJEICAO = "Renda Muito baixa";

	private static final long PROPOSTA_ID = 1003;
	private static final Long FUNCIONARIO_ID = 1000L;
	@EJB
	PropostaRepository propostaRepository;
	
	@EJB
	GerenteService gerenteService;
	
	@EJB
	ConfiguracaoRepository configuracaoRepository;
	
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
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/funcionario.xml",
	"db/propostas.xml"})
	@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/deveAceitarProposta.sql"})
	public void deveRejeitarProposta() {
		
		gerenteService.rejeitarProposta(Cargo.GERENTE, FUNCIONARIO_ID, PROPOSTA_ID, MOTIVO_REJEICAO);
		
		Proposta proposta = propostaRepository.findById(PROPOSTA_ID);
		
		Assert.assertEquals(proposta.getSituacao(), SituacaoProposta.REG);
	}

	@Test
	@UsingDataSet({"db/configuracao.xml"})
	public void deveAlterarHorarioConfiguracao(){
		
		Date horaInicialTransacao = Util.setTime(7, 0);
		Date horaFinalTransacao = Util.setTime(20, 0);
		gerenteService.alterarHorarioTransacao(horaInicialTransacao, horaFinalTransacao);
		
		Configuracao config = configuracaoRepository.findById(CONFIGURACAO_ID);
		
		Assert.assertEquals(config.getHoraInicialTransacao(), horaInicialTransacao);
		Assert.assertEquals(config.getHoraFinalTransacao(), horaFinalTransacao);
	}

}
