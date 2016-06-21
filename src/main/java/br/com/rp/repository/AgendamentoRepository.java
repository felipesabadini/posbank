package br.com.rp.repository;

import br.com.rp.domain.Agendamento;

public interface AgendamentoRepository extends Repository<Agendamento> {
	
	Agendamento encontrarAgendamentoAtivoPorID(Long id);
}
