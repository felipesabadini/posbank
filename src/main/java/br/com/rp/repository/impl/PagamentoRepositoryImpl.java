package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Pagamento;
import br.com.rp.repository.PagamentoRepository;

@Stateless
public class PagamentoRepositoryImpl extends AbstractRepositoryImpl<Pagamento> implements PagamentoRepository {

	public PagamentoRepositoryImpl() {
		super(Pagamento.class);
	}
	
}
