package br.com.rp.services;

import java.math.BigDecimal;
import java.util.List;

import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.TipoOperacao;

public interface MovimentacaoService {
	
	public List<Movimentacao> consultarMovimentacaoPorContaId(Long contaId);
	
	public Boolean realizarTransferencia(Long contaOrigemId, Long contaDestinoId, BigDecimal valor);
	
	public void registrarMovimentacao(Long contaId, BigDecimal valor, TipoOperacao tipoOperacao, TipoMovimentacao tipoMovimentacao);
}