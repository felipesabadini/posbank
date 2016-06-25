package br.com.rp.services;

import java.util.List;

import br.com.rp.domain.Cargo;
import br.com.rp.domain.Proposta;

public interface GerenteService {
	
	public List<Proposta> visualizarPropostasPorEstado(Cargo cargo, String estado);
	public void aceitarProposta(Cargo cargo, Long propostaId, String textoEmail);

	
}
