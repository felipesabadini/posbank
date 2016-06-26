package br.com.rp.services.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Cargo;
import br.com.rp.domain.Proposta;
import br.com.rp.services.ConfiguracaoService;
import br.com.rp.services.GerenteService;
import br.com.rp.services.PropostaService;

@Stateless
public class GerenteServiceImpl implements GerenteService{
	
	@EJB
	PropostaService propostaServiceImpl;
	
	@EJB
	ConfiguracaoService configuracaoService;
	
	@Override
	public List<Proposta> visualizarPropostasPorEstado(Cargo cargo, String estado) {
		if (cargo != Cargo.GERENTE) {
			return null;
		}
		
		return propostaServiceImpl.pesquisarPropostasPorEstado(estado);
		
	}

	@Override
	public void aceitarProposta(Cargo cargo, Long propostaId) {
		if (cargo != Cargo.GERENTE) {
			return;
		}
		
		propostaServiceImpl.aceitarProposta(propostaId);
		
	}

	@Override
	public void rejeitarProposta(Cargo cargo, Long funcionarioId, Long propostaId, String motivoRejicao) {
		
		if (cargo != Cargo.GERENTE) {
			return;
		}
		
		propostaServiceImpl.rejeitarProposta(propostaId, funcionarioId, motivoRejicao);		
	}

	@Override
	public void alterarHorarioTransacao(Date horaInicialTransacao, Date horaFinalTransacao) {

		configuracaoService.definirHorarioTransacaoBancaria(horaInicialTransacao, horaFinalTransacao);
		
	}
	
	
	
}
