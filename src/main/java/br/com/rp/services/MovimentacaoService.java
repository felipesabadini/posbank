package br.com.rp.services;

import java.math.BigDecimal;
import java.util.List;

import br.com.rp.domain.Movimentacao;

public interface MovimentacaoService {
	
	public List<Movimentacao> consultarMovimentacaoPorContaId(Long contaId);
	
	public void realizarTransferenciaOutrosBancos(Long contaId, BigDecimal valor, String codigoBanco, Long numeroConta);
	
	public void realizarTransferenciaEntreContasVBank(Long contaId, BigDecimal valor, Long contaDestinoId);
	
	public void realizarSaque(Long contaId, BigDecimal valor);
	
	public void realizarPagamento(Long contaId, Long pagamentoId);
}