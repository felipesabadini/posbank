package br.com.rp.services;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.rp.domain.LogMovimentacao;
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
	
	public void registrarTransferencia(Long contaId, BigDecimal valor, Long contaDestinoId){
		registrarLogMovimentacao(contaId, valor, TipoOperacao.TRANSFERENCIA, TipoMovimentacao.DEBITO, contaDestinoId);
	}
	
	public void registrarSaque(Long contaId, BigDecimal valor){
		registrarLogMovimentacao(contaId, valor, TipoOperacao.SAQUE, TipoMovimentacao.DEBITO, null);
	}
	
	public void registrarPagamento(Long contaId, BigDecimal valor){
		registrarLogMovimentacao(contaId, valor, TipoOperacao.PAGAMENTO, TipoMovimentacao.DEBITO, null);
	}	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private void registrarLogMovimentacao(Long contaId, BigDecimal valor, TipoOperacao tipoOperacao, TipoMovimentacao tipoMovimentacao, Long contaDestinoId){
		try {
			LogMovimentacao logMovimentacao = new LogMovimentacao();
			logMovimentacao.setConta(contaRepository.findById(contaId));
			logMovimentacao.setDataCadastro(Util.getDataHoraAtual());
			logMovimentacao.setTipoMovimentacao(tipoMovimentacao);
			logMovimentacao.setTipoOperacao(tipoOperacao);
			if (contaDestinoId != null){
				logMovimentacao.setContaDestino(contaRepository.findById(contaDestinoId));
			}
			
			repository.save(logMovimentacao);
		} catch (Exception e) {
			throw new LogMovimentacaoException(e);
		}
	
	}
}