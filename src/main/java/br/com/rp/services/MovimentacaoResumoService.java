package br.com.rp.services;

import java.util.List;

import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.MovimentacaoResumo;

public interface MovimentacaoResumoService {
	
	public void registrarMovimentacaoResumo(Movimentacao movimentacao);
	
	public List<MovimentacaoResumo> consultarMovimentacaoResumoNaoEnviadoBacen();

	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacen();
	
	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA();
	
	public void enviarFilaBacen();
	
	public void enviarFilaEUA();
}