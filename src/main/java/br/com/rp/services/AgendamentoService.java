package br.com.rp.services;

import javax.ejb.Local;

import br.com.rp.domain.Agendamento;

@Local
public interface AgendamentoService {
	Boolean agendarPagamento(Agendamento agendamento);
	Boolean cancelarPagamentoPorID(Long id);
}
