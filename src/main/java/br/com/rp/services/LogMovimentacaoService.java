package br.com.rp.services;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.rp.domain.Conta;
import br.com.rp.domain.LogMovimentacao;
import br.com.rp.domain.Pagamento;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.TipoOperacao;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.LogMovimentacaoRepository;
import br.com.rp.repository.PagamentoRepository;
import br.com.rp.services.exception.LogMovimentacaoException;
import br.com.rp.util.Constantes;
import br.com.rp.util.Util;

@Stateless
public class LogMovimentacaoService {
	
	@EJB
	LogMovimentacaoRepository repository;
	@EJB
	ContaRepository contaRepository;
	@EJB
	PagamentoRepository pagamentoRepository;
	
	public void registrarTransferenciaEntreContasVBank(Long contaId, BigDecimal valor, Long contaDestinoId){
		Conta contaDestino = contaRepository.findById(contaDestinoId);
		
		registrarLogMovimentacao(contaId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.DEBITO, Constantes.CODIGO_BANCO_VBANK, contaDestino.getNumero(), null, null);
	}
	
	public void registrarTransferenciaOutrosBancos(Long contaId, BigDecimal valor, String codigoDoBancoDestino, Long numeroContaDestino){
		registrarLogMovimentacao(contaId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.DEBITO, codigoDoBancoDestino, numeroContaDestino, null, null);
	}
	
	public void registrarPagamento(Long contaId, Long pagamentoId){
		Pagamento pagamento = pagamentoRepository.findById(pagamentoId);
		registrarLogMovimentacao(contaId, pagamento.getValor(), TipoOperacao.PAGAMENTO, TipoMovimentacao.DEBITO, null, null, pagamentoId, null);
	}	
	
	public void registrarDeposito(Long contaId, BigDecimal valor, String cmc7){
		registrarLogMovimentacao(contaId, valor, TipoOperacao.DEPOSITO, TipoMovimentacao.CREDITO, null, null, null, cmc7);
	}	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void registrarLogMovimentacao(Long contaId, BigDecimal valor, TipoOperacao tipoOperacao, TipoMovimentacao tipoMovimentacao, String codigoDoBanco, Long numeroContaDestino, Long pagamentoId, String cmc7){
		try {
			LogMovimentacao logMovimentacao = new LogMovimentacao();
			logMovimentacao.setConta(contaRepository.findById(contaId));
			logMovimentacao.setDataCadastro(Util.getDataHoraAtual());
			logMovimentacao.setValor(valor);
			logMovimentacao.setTipoOperacao(tipoOperacao);
			logMovimentacao.setTipoMovimentacao(tipoMovimentacao);
			logMovimentacao.setNumeroContaDestino(numeroContaDestino);
			logMovimentacao.setCodigoBanco(codigoDoBanco);
			if (pagamentoId != null && pagamentoId > 0){
				logMovimentacao.setPagamento(pagamentoRepository.findById(pagamentoId));
			}
			logMovimentacao.setCmc7(cmc7);
			
			repository.save(logMovimentacao);
		} catch (Exception e) {
			throw new LogMovimentacaoException(e);
		}
	}
}