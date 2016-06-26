package br.com.rp.services.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.Pagamento;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.TipoOperacao;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.MovimentacaoRepository;
import br.com.rp.repository.PagamentoRepository;
import br.com.rp.services.MovimentacaoResumoService;
import br.com.rp.services.MovimentacaoService;
import br.com.rp.services.exception.SaldoInsuficienteException;
import br.com.rp.util.Constantes;
import br.com.rp.util.Util;

@Stateless
public class MovimentacaoServiceImpl implements MovimentacaoService {

	@EJB
	private MovimentacaoRepository movimentacaoRepository;
	@EJB
	private ContaRepository contaRepository;
	@EJB
	private MovimentacaoResumoService movimentacaoResumoService;
	@EJB
	private PagamentoRepository pagamentoRepository;

	public List<Movimentacao> consultarMovimentacaoPorContaId(Long contaId) {
		return movimentacaoRepository.consultarMovimentacaoPorContaId(contaId);
	}

	public void realizarPagamento(Long contaId, Long pagamentoId) {
		Conta conta = contaRepository.findById(contaId);
		Pagamento pagamento = pagamentoRepository.findById(pagamentoId);
		validarSaldo(conta, pagamento.getValor());
		
		processoParaDebitarValorDaConta(conta, pagamento.getValor());
		registrarMovimentacao(contaId, pagamento.getValor(), TipoOperacao.PAGAMENTO, TipoMovimentacao.DEBITO, pagamentoId, null, null, null);
	}	

	private void processoParaDebitarValorDaConta(Conta conta, BigDecimal valor) {
		conta.setSaldo(conta.getSaldo().subtract(valor));
		contaRepository.save(conta);		
	}
	
	private void registrarMovimentacao(Long contaId, BigDecimal valor, TipoOperacao tipoOperacao, TipoMovimentacao tipoMovimentacao, Long pagamentoId, String codigoBanco, Long numeroContaDestino, String cmc7) {
		Conta conta = contaRepository.findById(contaId);

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setDataMovimentacao(Util.getDataHoraAtual());
		movimentacao.setTipoOperacao(tipoOperacao);
		movimentacao.setValor(valor);
		movimentacao.setTipoMovimentacao(tipoMovimentacao);
		if (pagamentoId != null && pagamentoId > 0){
			movimentacao.setPagamento(pagamentoRepository.findById(pagamentoId));
		}
		movimentacao.setCodigoBanco(codigoBanco);
		movimentacao.setNumeroContaDestino(numeroContaDestino);
		movimentacao.setCmc7(cmc7);

		movimentacaoRepository.save(movimentacao);
		
		movimentacaoResumoService.registrarMovimentacaoResumo(movimentacao);
	}

	private void validarSaldo(Conta conta, BigDecimal valor){
		if (conta.getSaldo().compareTo(valor) < 0){
			throw new SaldoInsuficienteException();
		}
	}

	public void realizarTransferencia(Long contaId, BigDecimal valor, String codigoBanco, Long numeroConta) {
		Conta contaOrigem = contaRepository.findById(contaId);
		
		validarSaldo(contaOrigem, valor);

		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaRepository.save(contaOrigem);
		registrarMovimentacao(contaId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.DEBITO, null, codigoBanco, numeroConta, null);
	}

	
	public void realizarTransferenciaEntreContasVBank(Long contaId, BigDecimal valor, Long contaDestinoId) {
		Conta contaOrigem = contaRepository.findById(contaId);
		Conta contaDestino = contaRepository.findById(contaDestinoId);
		
		validarSaldo(contaOrigem, valor);

		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaRepository.save(contaOrigem);
		registrarMovimentacao(contaId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.DEBITO, null, Constantes.CODIGO_BANCO_VBANK, contaDestino.getNumero(), null);

		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
		contaRepository.save(contaDestino);
		registrarMovimentacao(contaDestinoId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.CREDITO, null, null, null, null);
	}

	public void realizarDeposito(Long contaId, BigDecimal valor, String cmc7) {
		registrarMovimentacao(contaId, valor, TipoOperacao.DEPOSITO, TipoMovimentacao.CREDITO, null, null, null, cmc7);
	}
}