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
	
	public Boolean registrarNovoPagamento(Pagamento pagamento) {
		movimentacaoService.realizarPagamento(pagamento.getConta().getId(), pagamento.getId());
		pagamentoRepository.save(pagamento);
		return Boolean.TRUE;
	}
}