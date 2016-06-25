package br.com.rp.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Cargo;
import br.com.rp.domain.Proposta;
import br.com.rp.repository.PropostaRepository;
import br.com.rp.services.GerenteService;

@Stateless
public class GerenteServiceImpl implements GerenteService{
	
	@EJB
	PropostaRepository propostaRepository;
	
	@Override
	public List<Proposta> visualizarPropostasPorEstado(Cargo cargo, String estado) {
		if (cargo != Cargo.GERENTE) {
			return null;
		}
		
		return propostaRepository.procurarProspostasPorEstado(estado);
		
	}
	
}
