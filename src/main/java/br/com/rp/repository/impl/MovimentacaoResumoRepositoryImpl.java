package br.com.rp.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.repository.MovimentacaoResumoRepository;

@Stateless
public class MovimentacaoResumoRepositoryImpl extends AbstractRepositoryImpl<MovimentacaoResumo> implements MovimentacaoResumoRepository {

	public MovimentacaoResumoRepositoryImpl() {
		super(MovimentacaoResumo.class);
	}

	@SuppressWarnings("unchecked")
	public List<MovimentacaoResumo> consultarMovimentacaoResumoNaoEnviadoBacen() {
		Query query = em.createQuery("select m from MovimentacaoResumo m where m.enviadoBacen = false", MovimentacaoResumo.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacen() {
		Query query = em.createQuery("select m from MovimentacaoResumo m where m.enviadoBacen = true", MovimentacaoResumo.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA() {
		Query query = em.createQuery("select m from MovimentacaoResumo m where m.enviadoBacen = true and m.enviadoEUA = false", MovimentacaoResumo.class);
		return query.getResultList();
	}
	
}