package br.com.rp.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.SituacaoProposta;
import br.com.rp.repository.PropostaRepository;
import br.com.rp.util.Util;

@Stateless
public class PropostaRepositoryImpl extends AbstractRepositoryImpl<Proposta> implements PropostaRepository {

	public PropostaRepositoryImpl() {
		super(Proposta.class);
	}

	@Override
	public List<Proposta> procurarPorPropostasComMenosDe30DiasDoCliente(Cliente cliente) {			
		Query query = em.createQuery("from Proposta p where p.cliente = :cliente and p.dataCadastro >= :data", Proposta.class);
		query.setParameter("cliente", cliente);
		query.setParameter("data", Util.addData(-30, Util.getDataAtual()));
		
		return query.getResultList();
	}

	@Override
	public Boolean verificarSeOClienteJaEstaAtivo(Cliente cliente) {
		Query query = em.createQuery("from Proposta p where p.cliente.cpf = :cpf and p.situacao = :situacao", Proposta.class);
		query.setParameter("cpf", cliente.getCpf());
		query.setParameter("situacao", SituacaoProposta.AC);
		Object object = query.getSingleResult();
		if(object != null)
			return Boolean.TRUE;
		
		return Boolean.FALSE;
	}

	@Override
	public List<Proposta> procurarProspostasPorEstado(String estado) {
		
		Query query = em.createQuery("select p from Proposta p JOIN p.cliente c where c.endereco.uf = :estado", Proposta.class);
		query.setParameter("estado", estado);
		
		return query.getResultList();
	}

}
