package br.com.rp.services;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.TipoOperacao;

public interface MovimentacaoService {
	
	public List<Movimentacao> consultarMovimentacaoPorContaId(Long contaId);
	
	public Boolean realizarTransferencia(@NotNull Long contaOrigemId, @NotNull Long contaDestinoId, BigDecimal valor);
	
	public void registrarMovimentacao(Long contaId, BigDecimal valor, TipoOperacao tipoOperacao, TipoMovimentacao tipoMovimentacao);
}