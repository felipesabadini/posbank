package br.com.rp.services.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.TipoOperacao;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.MovimentacaoRepository;
import br.com.rp.services.LogMovimentacaoService;
import br.com.rp.services.MovimentacaoResumoService;
import br.com.rp.services.MovimentacaoService;
import br.com.rp.services.exception.SaldoInsuficienteException;
import br.com.rp.util.Util;

@Stateless
public class MovimentacaoServiceImpl implements MovimentacaoService {

	@EJB
	private MovimentacaoRepository movimentacaoRepository;
	@EJB
	private ContaRepository contaRepository;
	@EJB
	private LogMovimentacaoService logMovimentacaoService;
	@EJB
	private MovimentacaoResumoService movimentacaoResumoService;

	public List<Movimentacao> consultarMovimentacaoPorContaId(Long contaId) {
		return movimentacaoRepository.consultarMovimentacaoPorContaId(contaId);
	}

	public void realizarTransferencia(Long contaId, BigDecimal valor, Long contaDestinoId) {
		logMovimentacaoService.registrarTransferencia(contaId, valor, contaDestinoId);
		
		Conta contaOrigem = contaRepository.findById(contaId);
		Conta contaDestino = contaRepository.findById(contaDestinoId);
		
		validarSaldo(contaOrigem, valor);

		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaRepository.save(contaOrigem);
		registrarMovimentacao(contaId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.DEBITO);

		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
		contaRepository.save(contaDestino);
		registrarMovimentacao(contaDestinoId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.CREDITO);
	}

	public void realizarSaque(Long contaId, BigDecimal valor){
		logMovimentacaoService.registrarSaque(contaId, valor);
		Conta conta = contaRepository.findById(contaId);
		validarSaldo(conta, valor);
		
		processoParaDebitarValorDaConta(conta, valor);
		registrarMovimentacao(contaId, valor, TipoOperacao.SAQUE, TipoMovimentacao.DEBITO);
	}
	
	public void realizarPagamento(Long contaId, BigDecimal valor) {
		logMovimentacaoService.registrarPagamento(contaId, valor);
		Conta conta = contaRepository.findById(contaId);
		validarSaldo(conta, valor);
		
		processoParaDebitarValorDaConta(conta, valor);
		registrarMovimentacao(contaId, valor, TipoOperacao.PAGAMENTO, TipoMovimentacao.DEBITO);
	}	

	private void processoParaDebitarValorDaConta(Conta conta, BigDecimal valor) {
		conta.setSaldo(conta.getSaldo().subtract(valor));
		contaRepository.save(conta);		
	}
	
	private void registrarMovimentacao(Long contaId, BigDecimal valor, TipoOperacao tipoOperacao, TipoMovimentacao tipoMovimentacao) {
		Conta conta = contaRepository.findById(contaId);

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setDataMovimentacao(Util.getDataHoraAtual());
		movimentacao.setTipoOperacao(tipoOperacao);
		movimentacao.setValor(valor);
		movimentacao.setTipoMovimentacao(tipoMovimentacao);

		movimentacaoRepository.save(movimentacao);
		
		movimentacaoResumoService.registrarMovimentacaoResumo(movimentacao);
	}

	private void validarSaldo(Conta conta, BigDecimal valor){
		if (conta.getSaldo().compareTo(valor) < 0){
			throw new SaldoInsuficienteException();
		}
	}
}