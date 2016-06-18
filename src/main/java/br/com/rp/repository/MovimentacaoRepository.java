package br.com.rp.repository;

import java.util.List;

import br.com.rp.domain.Movimentacao;

public interface MovimentacaoRepository extends Repository<Movimentacao> {
	
	public List<Movimentacao> consultarMovimentacaoPorContaId(Long contaId);
	
}
