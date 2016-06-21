package br.com.rp.repository;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.rp.domain.Despesa;

public interface DespesaRepository extends Repository<Despesa> {
	
	public List<Despesa> consultarDespesasPorCartaoId(Long cartaoId);
	
	public BigDecimal consultarTotalDespesaPorCartaoIdAPartirDataInformada(Long cartaoId, Date data);	
}
