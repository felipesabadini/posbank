package br.com.rp.services.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Agendamento;
import br.com.rp.domain.Conta;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.repository.ContaRepository;
import br.com.rp.services.AgendamentoService;
import br.com.rp.services.MovimentacaoService;

@Stateless
public class AgendamentoServiceImpl implements AgendamentoService {
	
	@EJB
	private AgendamentoRepository agendamentoRepository;

	@EJB
	private MovimentacaoService movimentacaoService;
	
	@EJB
	private ContaRepository contaRepository;
	
	@Override	
	public Boolean agendarPagamento(Agendamento agendamento) {
		if(!agendamento.isAgendamentoValido())
			return Boolean.FALSE;
		
		if(agendamento.getConta().getId() != null) {
			Conta conta = this.contaRepository.findById(agendamento.getConta().getId());
			agendamento.setConta(conta);
			agendamento.getPagamento().setConta(conta);
		}			
			
		agendamentoRepository.save(agendamento);
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

	@Override
	public void processarPagamentosAgendadosPara(Date data) {
		List<Agendamento> agendamentos = this.agendamentoRepository.encontrarAgendamentosPara(data);
		
		agendamentos.forEach(ag -> {			
			movimentacaoService.realizarPagamento(ag.getConta().getId(), ag.getPagamento().getId());
			ag.setPago(Boolean.TRUE);
			this.agendamentoRepository.save(ag);
		});
		
	}

	@Override
	public List<Agendamento> encontrarAgendamentosPagos() {
		return this.agendamentoRepository.encontrarAgendamentosPagos();
	}

	
}
