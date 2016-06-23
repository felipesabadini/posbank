package br.com.rp.services;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.TipoConta;

@Local
public interface PropostaService {

	public Proposta processoParaRegistrarUmaProposta(Proposta proposta);	
	public void oClienteTemPropostaComMenosDe30Dias(Cliente cliente);
	public void oCPFDoClienteJaExisteEJaTemPropostaAceita(Cliente cliente);
	public List<Proposta> pesquisarPropostasPorEstado(String estado);
	public Proposta registrarProposta(Proposta proposta);
	public boolean aceitarProposta(Long propostaId, Long angenciaID, TipoConta tipoConta, BigDecimal limiteDaConta);
	public void rejeitarProposta(Long propostaId, String textoEmail);
}
