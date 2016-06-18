package br.com.rp.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.rp.domain.Movimentacao;
import br.com.rp.repository.MovimentacaoRepository;

@Stateless
public class MovimentacaoRepositoryImpl extends AbstractRepositoryImpl<Movimentacao> implements MovimentacaoRepository {

	public MovimentacaoRepositoryImpl() {
		super(Movimentacao.class);
	}

	@SuppressWarnings("unchecked")
	public List<Movimentacao> consultarMovimentacaoPorContaId(Long contaId) {
		Query query = em.createQuery("select m from Movimentacao m where m.Conta.id = :contaId", Movimentacao.class);
		query.setParameter("contaId", contaId);
		
		return query.getResultList();
	}
}