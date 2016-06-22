package br.com.rp.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.rp.domain.Agendamento;

@Local
public interface AgendamentoService {
	Boolean agendarPagamento(Agendamento agendamento);
	Boolean cancelarPagamentoPorID(Long id);
	void processarPagamentosAgendadosPara(Date date);
	List<Agendamento> encontrarAgendamentosPagos();
}
