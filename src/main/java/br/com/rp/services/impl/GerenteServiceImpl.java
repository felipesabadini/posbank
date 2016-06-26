package br.com.rp.services.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Cargo;
import br.com.rp.domain.Proposta;
import br.com.rp.services.ConfiguracaoService;
import br.com.rp.services.GerenteService;
import br.com.rp.services.PropostaService;
import br.com.rp.services.exception.NivelDeAcessoIncompativel;

@Stateless
public class GerenteServiceImpl implements GerenteService{
	
	@EJB
	PropostaService propostaServiceImpl;
	
	@EJB
	ConfiguracaoService configuracaoService;
	
	@Override
	public List<Proposta> visualizarPropostasPorEstado(Cargo cargo, String estado) {
		if (cargo != Cargo.GERENTE) {
			throw new NivelDeAcessoIncompativel("Seu nivel de acesso não permite realizar esta tarefa.");
		}
		
		return propostaServiceImpl.pesquisarPropostasPorEstado(estado);
		
	}

	@Override
	public void aceitarProposta(Cargo cargo, Long propostaId) {
		if (cargo != Cargo.GERENTE) {
			throw new NivelDeAcessoIncompativel("Seu nivel de acesso não permite realizar esta tarefa.");
		}
		
		propostaServiceImpl.aceitarProposta(propostaId);
		
	}

	@Override
	public void rejeitarProposta(Cargo cargo, Long funcionarioId, Long propostaId, String motivoRejicao) {
		
		if (cargo != Cargo.GERENTE) {
			throw new NivelDeAcessoIncompativel("Seu nivel de acesso não permite realizar esta tarefa.");
		}
		
		propostaServiceImpl.rejeitarProposta(propostaId, funcionarioId, motivoRejicao);		
	}

	@Override
	public void alterarHorarioTransacao(Cargo cargo, Date horaInicialTransacao, Date horaFinalTransacao) {

		if (cargo != Cargo.GERENTE_SEGURANCA) {
			throw new NivelDeAcessoIncompativel("Seu nivel de acesso não permite realizar esta tarefa.");
		}		
		
		configuracaoService.definirHorarioTransacaoBancaria(horaInicialTransacao, horaFinalTransacao);
		
	}
	
	
	
}
