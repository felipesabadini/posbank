package br.com.rp.services;

import java.time.LocalTime;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ConfiguracaoService {

	private LocalTime horaInicialTransacao;
	private LocalTime horaFinalTransacao;
	
	@PostConstruct
	public void init(){
		if (this.horaInicialTransacao == null && this.horaFinalTransacao == null){
			
		}
	}
	
	@Lock(LockType.WRITE)
	public void definirHorarioTransacaoBancaria(LocalTime horaInicialTransacao, LocalTime horaFinalTransacao){
		
	}
}