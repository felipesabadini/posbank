package br.com.rp.services;

import java.util.Date;
import java.util.List;

import br.com.rp.domain.Cargo;
import br.com.rp.domain.Proposta;

public interface GerenteService {
	
	public List<Proposta> visualizarPropostasPorEstado(Cargo cargo, String estado);
	public void aceitarProposta(Cargo cargo, Long propostaId);
	public void rejeitarProposta(Cargo cargo, Long funcionarioId, Long propostaId, String motivoRejicao);
	public void alterarHorarioTransacao(Date horaInicialTransacao, Date horaFinalTransacao);
}
