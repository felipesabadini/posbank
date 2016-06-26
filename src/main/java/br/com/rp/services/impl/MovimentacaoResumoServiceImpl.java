package br.com.rp.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.integration.MessageSender;
import br.com.rp.repository.MovimentacaoResumoRepository;
import br.com.rp.services.MovimentacaoResumoService;

@Stateless
public class MovimentacaoResumoServiceImpl implements MovimentacaoResumoService {
	@EJB
	private MovimentacaoResumoRepository repository;
	@EJB
	private MessageSender messageSender;
	
	public void registrarMovimentacaoResumo(Movimentacao movimentacao){
		MovimentacaoResumo movimentacaoResumo = new MovimentacaoResumo();
		movimentacaoResumo.setMovimentacao(movimentacao);
		movimentacaoResumo.setEnviadoBacen(Boolean.FALSE);
		movimentacaoResumo.setEnviadoEUA(Boolean.FALSE);
		
		repository.save(movimentacaoResumo);
	}

	public List<MovimentacaoResumo> consultarMovimentacaoResumoNaoEnviadoBacen() {
		return repository.consultarMovimentacaoResumoNaoEnviadoBacen();
	}

	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacen() {
		return repository.consultarMovimentacaoResumoEnviadoBacen();
	}

	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA() {
		return repository.consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA();
	}

	public void enviarFilaBacen() {
		List<MovimentacaoResumo> lstMovimentacaoResumo = repository.consultarMovimentacaoResumoNaoEnviadoBacen();
		lstMovimentacaoResumo.forEach(movimentacaoResumo -> {
			messageSender.realizarIntegracaoBACEN(movimentacaoResumo);
		});
	}
	
	public void enviarFilaEUA() {
		List<MovimentacaoResumo> lstMovimentacaoResumo = repository.consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA(); 
		lstMovimentacaoResumo.forEach(movimentacaoResumo -> {
			messageSender.realizarIntegracaoEUA(movimentacaoResumo);
		});
	}
}