package br.com.rp.repository;

import java.util.List;

import br.com.rp.domain.MovimentacaoResumo;

public interface MovimentacaoResumoRepository extends Repository<MovimentacaoResumo> {
	
	public List<MovimentacaoResumo> consultarMovimentacaoResumoNaoEnviadoBacen();
	
	public List<MovimentacaoResumo> consultarMovimentacaoResumoNaoEnviadoBacen(Integer registroInicial, Integer limite);

	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacen();
	
	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA();
	
	public Integer consultarQuantidadeMovimentacaoResumoNaoEnviadoBacen();
	
	public Integer consultarQuantidadeMovimentacaoResumoEnviadoBacenNaoEnviadoEUA();
}