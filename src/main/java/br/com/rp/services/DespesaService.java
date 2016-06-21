package br.com.rp.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.rp.domain.Despesa;

public interface DespesaService {
	
	public List<Despesa> consultarDespesasPorCartaoId(Long cartaoId);
	
	public BigDecimal consultarTotalDespesaPorCartaoIdAPartirDataInformada(Long cartaoId, Date data);
	
	public void registrarDespesa(Long cartaoId, String descricao, BigDecimal valor);
}
