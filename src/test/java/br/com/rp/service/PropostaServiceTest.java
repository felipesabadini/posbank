package br.com.rp.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Cpf;
import br.com.rp.domain.Email;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.SituacaoCliente;
import br.com.rp.domain.SituacaoProposta;
import br.com.rp.domain.TipoConta;
import br.com.rp.repository.ClienteRepository;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.PropostaRepository;
import br.com.rp.services.PropostaService;
import br.com.rp.services.exception.ClienteComPropostaComMenosDe30DiasException;
import br.com.rp.services.exception.ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException;


public class PropostaServiceTest extends AbstractTest {

	private static final String PROPOSTA_REJEITADA = "FOi MAL AI MAS FOI REJEITADA. :-/";
	private static final String VALOR_LIMETE_CONTA = "3000";
	private static final long AGENCIA_ID = 1000L;
	private static final Long ID_PROPOSTA = 1003L;
	private static final int QUANTIDA_PROSPOSTA_PR = 4;
	@EJB
	private PropostaService propostaService;
	@EJB
	private ClienteRepository clienteRepository;
	private final String ESTADO_CLIENTE = "PR";
	 
	@EJB
	ContaRepository contaRepository;
	
	@EJB
	PropostaRepository propostaRepository;
	
	
	@Test(expected = ClienteComPropostaComMenosDe30DiasException.class)
	@UsingDataSet({"db/cliente.xml", "db/funcionario.xml", "db/propostas.xml"})
	public void deveLancarUmExceptionCasoJaExistaPropostaParaOClienteComMenosDe30Dias() {
		Cliente cliente = this.clienteRepository.findById(100L);
		propostaService.oClienteTemPropostaComMenosDe30Dias(cliente);	
	}
	
	@Test(expected = ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException.class)
	@UsingDataSet({"db/cliente.xml", "db/funcionario.xml", "db/propostas.xml"})
	public void deveLancarUmaExceptionCasoOClienteJaEstajaAtivoETentarFazerUmaNovaProposta() {
		Cliente cliente = this.clienteRepository.findById(100L);
		propostaService.oCPFDoClienteJaExisteEJaTemPropostaAceita(cliente);	
	}	
	
	@Test
	public void deveRegistrarUmaProposta() {
		Cliente cliente = new Cliente();
		cliente.setCpf(new Cpf("77043980593"));
		cliente.setNome("Maria");
		cliente.setEmail(new Email("maria@maria.com.br"));
		
		Proposta proposta = new Proposta();
		proposta.setCliente(cliente);
		proposta.setMensagem("Quero muito ser cliente desse banco !");
		proposta.setRendimento(new BigDecimal("1500.55"));

		this.propostaService.registrarProposta(proposta);
		
		Assert.assertNotNull(cliente.getId());
		Assert.assertNotNull(proposta.getId());
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/funcionario.xml",
		"db/propostas.xml"})
	public void deveRetornarProspostarPorEstado() {

		List<Proposta> propostas =  propostaService.pesquisarPropostasPorEstado(ESTADO_CLIENTE);
		
		Assert.assertEquals(QUANTIDA_PROSPOSTA_PR, propostas.size());
		
	}	
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/banco.xml","db/agencia.xml","db/conta.xml", "db/funcionario.xml", "db/propostas.xml"})
	@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/deveEnviarEmail.sql"})
	public void deveEnviarEmail() {
		
		boolean resultado = propostaService.aceitarProposta(ID_PROPOSTA, AGENCIA_ID, TipoConta.CC, new BigDecimal(VALOR_LIMETE_CONTA));
		Proposta proposta = propostaRepository.findById(ID_PROPOSTA);
		
		Cliente cliente = clienteRepository.findById(proposta.getCliente().getId());
		
		List<Conta> contas = contaRepository.getAll();
		
		System.out.println(cliente.getSenha());		
		
		Assert.assertEquals(SituacaoCliente.ATIVO, cliente.getSituacao());		
		
		Assert.assertEquals(true, resultado);
		
		Assert.assertEquals(SituacaoProposta.AC, proposta.getSituacao());
	}
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/funcionario.xml", "db/propostas.xml"})
	@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/deveRejeitarProposta.sql"})
	public void deveRejeitarProposta() {
		
		propostaService.rejeitarProposta(ID_PROPOSTA, PROPOSTA_REJEITADA);
		
		Proposta propostaRejeitada = propostaRepository.findById(ID_PROPOSTA);
		
		Assert.assertEquals(propostaRejeitada.getSituacao(), SituacaoProposta.REG);
	}
	
}
