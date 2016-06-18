package br.com.rp.services;

import javax.ejb.Local;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Proposta;

@Local
public interface PropostaService {

	public void processoParaRegistrarUmaProposta(Proposta proposta);
	public void oClienteTemPropostaComMenosDe30Dias(Cliente cliente);
	public void oCPFDoClienteJaExisteEJaTemPropostaAceita(Cliente cliente);
	public void registrarProposta(Proposta proposta);
}
