package br.com.rp.services;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TimerService;

@Singleton
@Startup
public class IntegradorGeral {
	
	@Resource
	private TimerService timerService;
	
	@PostConstruct
	public void init(){
		
	}
	
}