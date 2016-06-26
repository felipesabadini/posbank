package br.com.rp.services;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.rp.domain.LogMovimentacao;
import br.com.rp.domain.Pagamento;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.TipoOperacao;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.LogMovimentacaoRepository;
import br.com.rp.services.exception.LogMovimentacaoException;
import br.com.rp.util.Util;

@Stateless
public class LogMovimentacaoService {
	
	@EJB
	LogMovimentacaoRepository repository;
	@EJB
	ContaRepository contaRepository;
	
	public void registrarTransferencia(Long contaId, BigDecimal valor, Long contaDestinoId, String codigoDoBanco){
		registrarLogMovimentacao(contaId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.DEBITO, contaDestinoId, codigoDoBanco, null);
	}
	
	public void registrarSaque(Long contaId, BigDecimal valor){
		registrarLogMovimentacao(contaId, valor, TipoOperacao.SAQUE, TipoMovimentacao.DEBITO, null, null, null);
	}
	
	public void registrarPagamento(Long contaId, BigDecimal valor, Pagamento pagamento){
		registrarLogMovimentacao(contaId, valor, TipoOperacao.PAGAMENTO, TipoMovimentacao.DEBITO, null, null, pagamento);
	}	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void registrarLogMovimentacao(Long contaId, BigDecimal valor, TipoOperacao tipoOperacao, TipoMovimentacao tipoMovimentacao, Long contaDestinoId, String codigoDoBanco, Pagamento pagamento){
		try {
			LogMovimentacao logMovimentacao = new LogMovimentacao();
			logMovimentacao.setConta(contaRepository.findById(contaId));
			logMovimentacao.setDataCadastro(Util.getDataHoraAtual());
			logMovimentacao.setTipoMovimentacao(tipoMovimentacao);
			logMovimentacao.setTipoOperacao(tipoOperacao);
			logMovimentacao.setValor(valor);
			logMovimentacao.setPagamento(pagamento);
			logMovimentacao.setNumeroContaDestino(contaDestinoId);
			logMovimentacao.setCodigoBanco(codigoDoBanco);
//			if (contaDestinoId != null){
//				logMovimentacao.setContaDestino(contaRepository.findById(contaDestinoId));
//			}
			
			repository.save(logMovimentacao);
		} catch (Exception e) {
			throw new LogMovimentacaoException(e);
		}
	
	}
}