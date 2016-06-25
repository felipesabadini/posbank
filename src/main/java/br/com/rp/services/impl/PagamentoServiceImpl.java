package br.com.rp.services.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Pagamento;
import br.com.rp.repository.PagamentoRepository;
import br.com.rp.services.MovimentacaoService;
import br.com.rp.services.PagamentoService;

@Stateless
public class PagamentoServiceImpl implements PagamentoService {

	@EJB
	private PagamentoRepository pagamentoRepository;
	
	@EJB
	private MovimentacaoService movimentacaoService;
	
	@Override
	public Boolean registrarNovoPagamento(Pagamento pagamento) {
		this.movimentacaoService.realizarPagamento(pagamento.getConta().getId(), pagamento.getValor());
		this.pagamentoRepository.save(pagamento);
		return Boolean.TRUE;
	}

}
