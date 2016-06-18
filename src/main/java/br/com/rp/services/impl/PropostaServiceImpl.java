package br.com.rp.services.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Proposta;
import br.com.rp.services.PropostaService;
import br.com.rp.services.exceptions.ClienteComPropostaComMenosDe30DiasException;
import br.com.rp.services.exceptions.ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException;

@Stateless
public class PropostaServiceImpl implements PropostaService {

	
	@Override
	public void processoParaRegistrarUmaProposta(Proposta proposta) {
		oCPFDoClienteJaExisteEJaTemPropostaAceita(proposta.getCliente());
		oClienteTemPropostaComMenosDe30Dias(proposta.getCliente());
		
		
	}

	@Override
	public void oClienteTemPropostaComMenosDe30Dias(Cliente cliente) {
		throw new ClienteComPropostaComMenosDe30DiasException("Você só pode enviar uma nova proposta, apos 30 dias da prospota que voce já enviou.");
	}

	@Override
	public void oCPFDoClienteJaExisteEJaTemPropostaAceita(Cliente cliente) {
		throw new ClienteJaAtivoTentandoRegistrarUmaNovaPropostaException("Você já um cliente do nosso banco, você não pode enviar novas proposta.");
	}

	@Override
	public void registrarProposta(Proposta proposta) {
		
	}

}
