package br.com.rp.services;

import java.util.List;

import javax.ejb.Local;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Proposta;

@Local
public interface PropostaService {

	public Proposta processoParaRegistrarUmaProposta(Proposta proposta);	
	public void oClienteTemPropostaComMenosDe30Dias(Cliente cliente);
	public void oCPFDoClienteJaExisteEJaTemPropostaAceita(Cliente cliente);
	public List<Proposta> pesquisarPropostasPorEstado(String estado);
	public Proposta registrarProposta(Proposta proposta);
	public boolean aceitarProposta(Long propostaId);
	public void rejeitarProposta(Long propostaId, Long funcionarioId, String motivoRejeicao);
}
