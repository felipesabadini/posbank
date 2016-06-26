
package br.com.rp.services.impl;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.rp.services.IntegradorService;
import br.com.rp.services.MovimentacaoResumoService;

@Singleton
@Startup
public class IntegradorServiceImpl implements IntegradorService {

	@EJB
	private MovimentacaoResumoService movimentacaoResumoService;
	
	@PostConstruct
	private void init(){
		// ...
	}
	
	public void enviarMovimentacaoBancoCentral() {
		movimentacaoResumoService.enviarFilaBacen();
	}

	public void enviarMovimentacaoEUA() {
		movimentacaoResumoService.enviarFilaEUA();
	}
}