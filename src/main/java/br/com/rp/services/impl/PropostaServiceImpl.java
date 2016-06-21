package br.com.rp.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Proposta;
import br.com.rp.repository.PropostaRepository;
import br.com.rp.services.PropostaService;
import br.com.rp.services.exception.ClienteComPropostaComMenosDe30DiasException;
import br.com.rp.services.exception.ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException;

@Stateless
public class PropostaServiceImpl implements PropostaService {
	
	@EJB
	private PropostaRepository propostaRepository;
	
	@Override
	public void processoParaRegistrarUmaProposta(Proposta proposta) {
		oCPFDoClienteJaExisteEJaTemPropostaAceita(proposta.getCliente());
		oClienteTemPropostaComMenosDe30Dias(proposta.getCliente());
		registrarProposta(proposta);						
	}
	
	@Override	
	public void oClienteTemPropostaComMenosDe30Dias(Cliente cliente) {		
		List<Proposta> propostas = this.propostaRepository.procurarPorPropostasComMenosDe30DiasDoCliente(cliente);
		if(propostas.size() > 0)
			throw new ClienteComPropostaComMenosDe30DiasException("Você só pode enviar uma nova proposta, apos 30 dias da prospota que voce já enviou.");
	}

	@Override
	public void oCPFDoClienteJaExisteEJaTemPropostaAceita(Cliente cliente) {
		if(this.propostaRepository.verificarSeOClienteJaEstaAtivo(cliente))
			throw new ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException("Você já um cliente do nosso banco, você não pode enviar novas proposta.");
	}

	@Override
	public void registrarProposta(Proposta proposta) {
		this.propostaRepository.save(proposta);
	}
	
	public List<Proposta> pesquisarPropostasPorEstado(String estado) {

		List<Proposta> propostas = propostaRepository.procurarProspostasPorEstado(estado);

		return propostas;
	}

}
//Preciso implementar o teste do methodo procurarProspostasPorEstado da class PropostaRepositoryImpl


//pesquisarporposta por estado que estão com situações recebidas usar a enum
//acietar proposta(ID da proposta)  ===>  mudar o status de cleinte para ativo ===>  criar conta corrente numero da conta random inserir o cliente ativado====> enviar email
//rejeitar proposta(ID da proposta, string motivo rejeição) ===> informar o motivo da rejeição ===> enviar e-mail
//enviar e-mail