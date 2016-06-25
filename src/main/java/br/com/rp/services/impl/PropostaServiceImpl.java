package br.com.rp.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.SituacaoCliente;
import br.com.rp.domain.SituacaoProposta;
import br.com.rp.domain.TipoConta;
import br.com.rp.repository.AgenciaRepository;
import br.com.rp.repository.ClienteRepository;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.PropostaRepository;
import br.com.rp.services.EmailService;
import br.com.rp.services.PropostaService;
import br.com.rp.services.exception.ClienteComPropostaComMenosDe30DiasException;
import br.com.rp.services.exception.ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException;
import br.com.rp.util.Util;

@Stateless
public class PropostaServiceImpl implements PropostaService {
	
	private static final String PROPOSTA_ACEITA = "Proposta aceita!";
	private static final String PROPOSTA_REJEITADA = "Proposta rejeitada.";

	@EJB
	private PropostaRepository propostaRepository;
	
	@EJB
	EmailService emailService;
	
	@EJB
	ClienteRepository clienteRepository;
	
	@EJB
	ContaRepository contaRepository;
	
	@EJB
	AgenciaRepository agenciaRepository;
	
	@Override
	public Proposta processoParaRegistrarUmaProposta(Proposta proposta) {
		oCPFDoClienteJaExisteEJaTemPropostaAceita(proposta.getCliente());
		oClienteTemPropostaComMenosDe30Dias(proposta.getCliente());
		registrarProposta(proposta);
		return proposta;
	}
	
	@Override	
	public void oClienteTemPropostaComMenosDe30Dias(Cliente cliente) {		
		if(cliente.getId() != null) {
			List<Proposta> propostas = this.propostaRepository.procurarPorPropostasComMenosDe30DiasDoCliente(cliente);
			if(propostas.size() > 0)
				throw new ClienteComPropostaComMenosDe30DiasException("Você só pode enviar uma nova proposta, apos 30 dias da prospota que voce já enviou.");
		}
	}

	@Override
	public void oCPFDoClienteJaExisteEJaTemPropostaAceita(Cliente cliente) {
		if(cliente.getId() != null) {
			if(this.propostaRepository.verificarSeOClienteJaEstaAtivo(cliente))
				throw new ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException("Você já um cliente do nosso banco, você não pode enviar novas proposta.");
		}
	}

	@Override
	public Proposta registrarProposta(Proposta proposta) {
		return this.propostaRepository.save(proposta);
	}
	
	public List<Proposta> pesquisarPropostasPorEstado(String estado) {

		List<Proposta> propostas = propostaRepository.procurarProspostasPorEstado(estado);

		return propostas;
	}

	@Override
	public boolean aceitarProposta(Long propostaId, TipoConta tipoConta, BigDecimal limiteDaConta) {
		
		Proposta proposta = propostaRepository.findById(propostaId);
		proposta.setSituacao(SituacaoProposta.AC);

		propostaRepository.save(proposta);	
		
		Cliente cliente = clienteRepository.findById(proposta.getCliente().getId());
		
		cliente.setSituacao(SituacaoCliente.ATIVO);
		String senhaPrimeiroAcesso = Util.formataData(cliente.getNascimento(), "dd/MM/yyyy").replace("/", "") + cliente.getNome().substring(0, 1);
		cliente.setSenha(senhaPrimeiroAcesso);
		
		clienteRepository.save(cliente);
		
		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setLimite(limiteDaConta);
		long numeroDaConta = Math.abs((100000 + new Random().nextInt() * 900000));
		conta.setNumero(numeroDaConta);
		conta.setTipoConta(tipoConta);
		conta.setSaldo(new BigDecimal("0"));
		
		contaRepository.save(conta);
		
		String textoEmail = "Parabéns sua proposta foi aceita!! \nSegue número da conta e senha para primeiro acesso:\n\nconta: " + numeroDaConta + "\nsenha: " + senhaPrimeiroAcesso ;
		
		try {
			emailService.enviarEmail(cliente.getEmail().toString(), PROPOSTA_ACEITA, textoEmail);
		} catch (MessagingException e) {
			//TODO criar uma error personalizado para erro d eenvio de e-mail.
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void rejeitarProposta(Long propostaId, String textoEmail) {

		Proposta proposta = propostaRepository.findById(propostaId);
		proposta.setSituacao(SituacaoProposta.REG);
		
		propostaRepository.save(proposta);
		
		Cliente cliente = clienteRepository.findById(proposta.getCliente().getId());
		
		try {
			emailService.enviarEmail(cliente.getEmail().toString(), PROPOSTA_REJEITADA, textoEmail);
		} catch (MessagingException e) {
			//TODO criar uma error personalizado para erro d eenvio de e-mail.
			e.printStackTrace();
		}
	}
	
}
