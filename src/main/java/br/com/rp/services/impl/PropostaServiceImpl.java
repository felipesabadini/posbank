package br.com.rp.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.SituacaoProposta;
import br.com.rp.repository.ClienteRepository;
import br.com.rp.repository.PropostaRepository;
import br.com.rp.services.EmailService;
import br.com.rp.services.PropostaService;
import br.com.rp.services.exception.ClienteComPropostaComMenosDe30DiasException;
import br.com.rp.services.exception.ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException;

@Stateless
public class PropostaServiceImpl implements PropostaService {
	
	@EJB
	private PropostaRepository propostaRepository;
	
	@EJB
	EmailService emailService;
	
	@EJB
	ClienteRepository clienteRepository;

	
	
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
	public boolean aceitarProposta(Long propostaId) {
		
		Proposta proposta = propostaRepository.findById(propostaId);
		proposta.setSituacao(SituacaoProposta.AC);

		propostaRepository.save(proposta);		

		Cliente cliente = clienteRepository.findById(proposta.getCliente().getId());
		
		
		
		
		try {
			emailService.enviarEmail("douglas-da.silva.santos@hotmail.com", "Teste", "conteudo");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	

	
}
//Preciso implementar o teste do methodo procurarProspostasPorEstado da class PropostaRepositoryImpl


//pesquisarporposta por estado que estão com situações recebidas usar a enum
//acietar proposta(ID da proposta)  ===>  mudar o status de cleinte para ativo ===>  criar conta corrente numero da conta random inserir o cliente ativado====> enviar email
//rejeitar proposta(ID da proposta, string motivo rejeição) ===> informar o motivo da rejeição ===> enviar e-mail
//enviar e-mail