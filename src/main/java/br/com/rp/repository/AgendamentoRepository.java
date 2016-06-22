package br.com.rp.repository;

import java.util.Date;
import java.util.List;

import br.com.rp.domain.Agendamento;

public interface AgendamentoRepository extends Repository<Agendamento> {
	
	Agendamento encontrarAgendamentoAtivoPorID(Long id);
	List<Agendamento> encontrarAgendamentosPara(Date data);
	List<Agendamento> encontrarAgendamentosPagos();
}
