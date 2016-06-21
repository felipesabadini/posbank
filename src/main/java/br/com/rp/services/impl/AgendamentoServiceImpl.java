package br.com.rp.services.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Agendamento;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.services.AgendamentoService;

@Stateless
public class AgendamentoServiceImpl implements AgendamentoService {
	
	@EJB
	private AgendamentoRepository agendamentoRepository;

	@Override
	public Boolean agendarPagamento(Agendamento agendamento) {
		System.out.println("-------->" + agendamento.isAgendamentoValido());
		if(!agendamento.isAgendamentoValido())
			return Boolean.FALSE;
			
			this.agendamentoRepository.save(agendamento);
			return Boolean.TRUE;
	}

	@Override
	public Boolean cancelarPagamentoPorID(Long id) {
		Agendamento agendamento = this.agendamentoRepository.encontrarAgendamentoAtivoPorID(id);
		if(agendamento == null) return Boolean.FALSE;
		
		agendamento.setCancelado(Boolean.TRUE);
		this.agendamentoRepository.save(agendamento);
		
		return Boolean.TRUE;
	}

	
}
