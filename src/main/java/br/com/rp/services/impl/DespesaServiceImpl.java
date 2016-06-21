package br.com.rp.services.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Cartao;
import br.com.rp.domain.Despesa;
import br.com.rp.repository.CartaoRepository;
import br.com.rp.repository.DespesaRepository;
import br.com.rp.services.DespesaService;
import br.com.rp.services.exception.LimiteCartaoInsuficienteException;
import br.com.rp.util.Util;

@Stateless
public class DespesaServiceImpl implements DespesaService {
	
	private final static Integer PERIODO_DESPESA = -27;
	
	@EJB
	private DespesaRepository despesaRepository;
	
	@EJB
	private CartaoRepository cartaoRepository;

	public List<Despesa> consultarDespesasPorCartaoId(Long cartaoId) {
		return despesaRepository.consultarDespesasPorCartaoId(cartaoId);
	}
	
	public BigDecimal consultarTotalDespesaPorCartaoIdAPartirDataInformada(Long cartaoId, Date data) {
		return despesaRepository.consultarTotalDespesaPorCartaoIdAPartirDataInformada(cartaoId, data);
	}
	
	@Override
	public void registrarDespesa(Long cartaoId, String descricao, BigDecimal valor) {
		
		Cartao cartao = cartaoRepository.findById(cartaoId);
		Calendar calendar = Calendar.getInstance();
		Integer diaAtual = calendar.get(Calendar.DATE);
		
		if (diaAtual < cartao.getDiaVencimento()){
			calendar.set(Calendar.DATE, cartao.getDiaVencimento());
		}else{
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DATE, cartao.getDiaVencimento());
		}
		
		calendar.add(Calendar.DATE, PERIODO_DESPESA);
		
		BigDecimal totalGasto = consultarTotalDespesaPorCartaoIdAPartirDataInformada(cartaoId, calendar.getTime());
		totalGasto = totalGasto.add(valor);
		if (cartao.getLimite().compareTo(totalGasto) > 0){
			throw new LimiteCartaoInsuficienteException();
		}
		
		Despesa despesa = new Despesa();
		despesa.setCartao(cartao);
		despesa.setDataLancamento(Util.getDataHoraAtual());
		despesa.setDescricao(descricao);
		despesa.setValor(valor);
		
		despesaRepository.save(despesa);
	}
}