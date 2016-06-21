package br.com.rp.repository.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.rp.domain.Despesa;
import br.com.rp.repository.DespesaRepository;

@Stateless
public class DespesaRepositoryImpl extends AbstractRepositoryImpl<Despesa> implements DespesaRepository {

	public DespesaRepositoryImpl() {
		super(Despesa.class);
	}

	@SuppressWarnings("unchecked")
	public List<Despesa> consultarDespesasPorCartaoId(Long cartaoId) {
		Query query = em.createQuery("select d from Despesa d where d.cartao.id = :cartaoId", Despesa.class);
		query.setParameter("cartaoId", cartaoId);
		
		return query.getResultList();
	}

	public BigDecimal consultarTotalDespesaPorCartaoIdAPartirDataInformada(Long cartaoId, Date data) {
		Query query = em.createQuery("select coalesce(sum(d.valor), 0) from Despesa d where cast(d.dataLancamento as date) >= :data");
		return (BigDecimal) query.getSingleResult();
	}

}
