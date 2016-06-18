package br.com.rp.services.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.TipoOperacao;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.MovimentacaoRepository;
import br.com.rp.services.MovimentacaoService;
import br.com.rp.services.exception.SaldoInsuficienteException;
import br.com.rp.util.Util;

@Stateless
public class MovimentacaoServiceImpl implements MovimentacaoService {
	
	@EJB
	private MovimentacaoRepository movimentacaoRepository;
	@EJB
	private ContaRepository contaRepository;
	
	public List<Movimentacao> consultarMovimentacaoPorContaId(Long contaId) {
		return movimentacaoRepository.consultarMovimentacaoPorContaId(contaId);
	}

	public Boolean realizarTransferencia(@NotNull Long contaOrigemId, @NotNull Long contaDestinoId, BigDecimal valor) {
		Conta contaOrigem = contaRepository.findById(contaOrigemId);
		Conta contaDestino = contaRepository.findById(contaDestinoId);
		
		if (contaOrigem.getSaldo().compareTo(valor) < 0){
			throw new SaldoInsuficienteException();
		}
		
		//Reduzir o saldo da conta origem
		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaRepository.save(contaOrigem);
		registrarMovimentacao(contaOrigemId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.DEBITO);
		
		//Acrescentar o saldo na conta de destino
		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
		contaRepository.save(contaDestino);
		registrarMovimentacao(contaDestinoId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.CREDITO);
		
		return Boolean.TRUE;
	}

	public void registrarMovimentacao(Long contaId, BigDecimal valor, TipoOperacao tipoOperacao, TipoMovimentacao tipoMovimentacao) {
		Conta conta = contaRepository.findById(contaId);
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setDataMovimentacao(Util.getDataHoraAtual());
		movimentacao.setTipoOperaacao(tipoOperacao);
		movimentacao.setValor(valor);
		movimentacao.setTipoMovimentacao(tipoMovimentacao);
		
		movimentacaoRepository.save(movimentacao);
	}
}