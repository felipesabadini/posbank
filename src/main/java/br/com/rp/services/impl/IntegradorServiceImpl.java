
package br.com.rp.services.impl;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.rp.services.IntegradorService;
import br.com.rp.services.MovimentacaoResumoService;

@Singleton
@Startup
public class IntegradorServiceImpl implements IntegradorService {

	@EJB
	private MovimentacaoResumoService movimentacaoResumoService;
	
	@Schedule(minute="*/5")
	public void enviarMovimentacaoBancoCentral() {
		movimentacaoResumoService.enviarFilaBacen();
	}

	@Schedule(hour="22")
	public void enviarMovimentacaoEUA() {
		movimentacaoResumoService.enviarFilaEUA();
	}
}