package br.com.rp.services.impl;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;
import br.com.rp.services.ConfiguracaoService;
import br.com.rp.services.exception.HorarioInfornadoNaoPermitidoException;
import br.com.rp.util.Util;

@Singleton
@Startup
public class ConfiguracaoServiceImpl implements ConfiguracaoService{

	private static final long CONFIGURA_ID = 1L;
	private static final int MINUTO_INICIAL = 0;
	private static final int HORA_INICIAL = 6;
	private static final int MINUTO_FINAL = 30;
	private static final int HORA_FINAL = 21;
	private Date horaInicialTransacao = Util.setTime(HORA_INICIAL, MINUTO_INICIAL);
	private Date horaFinalTransacao = Util.setTime(HORA_FINAL, MINUTO_FINAL);
	
	Configuracao configuracao = new Configuracao();	
	
	@EJB
	ConfiguracaoRepository dao; 
	
	@PostConstruct
	public void init(){
		
		Configuracao config = dao.findById(CONFIGURA_ID);
		
		if (config != null) {
			configuracao.setHoraInicialTransacao(config.getHoraInicialTransacao());
			configuracao.setHoraFinalTransacao(config.getHoraFinalTransacao());
		}
		else {
			configuracao.setHoraInicialTransacao(horaInicialTransacao);
			configuracao.setHoraFinalTransacao(horaFinalTransacao);
		}
		
	}
	
	@Lock(LockType.WRITE)
	public void definirHorarioTransacaoBancaria(Date horaInicialTransacao, Date horaFinalTransacao){
		if (horaInicialTransacao.after(horaFinalTransacao)) {
			throw new HorarioInfornadoNaoPermitidoException("A hora inicial não pode ser posterior à hora final!");
		}

		this.horaInicialTransacao = horaInicialTransacao;
		this.horaFinalTransacao = horaFinalTransacao;
		
		configuracao.setHoraInicialTransacao(horaInicialTransacao);
		configuracao.setHoraFinalTransacao(horaFinalTransacao);
		
		dao.save(configuracao);
	}

	@Lock(LockType.READ)
	public Date getHoraInicialTransacao() {
		return horaInicialTransacao;
	}

	@Lock(LockType.READ)
	public Date getHoraFinalTransacao() {
		return horaFinalTransacao;
	}

	@Lock(LockType.READ)
	public Configuracao getConfiguracao() {
		return configuracao;
	}

}