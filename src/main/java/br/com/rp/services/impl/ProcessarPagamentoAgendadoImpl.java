package br.com.rp.services.impl;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import br.com.rp.services.AgendamentoService;
import br.com.rp.services.ProcessarPagamentoAgendado;
import br.com.rp.util.Util;

@Singleton
public class ProcessarPagamentoAgendadoImpl implements ProcessarPagamentoAgendado {
	
	@EJB
	private AgendamentoService agendamentoService;

	@Override
	@Schedule(dayOfMonth = "Mon-Fri", hour = "16" )
	public void processarPagamento() {
		this.agendamentoService.processarPagamentosAgendadosPara(Util.getDataAtual());
	}

}
