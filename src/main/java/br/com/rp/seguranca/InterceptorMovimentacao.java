package br.com.rp.seguranca;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.rp.services.ConfiguracaoService;
import br.com.rp.services.exception.TransacaoForaHorarioPermitidoException;
import br.com.rp.util.Util;

@Interceptor
public class InterceptorMovimentacao {

	@EJB
	ConfiguracaoService configuracaoService;
	
	@AroundInvoke
	public Object validarHorarioTransacao(InvocationContext context) throws Exception {
		if (!Util.isDateBetweenDate1andDate2(Util.getDataAtual(), configuracaoService.getHoraInicialTransacao(), configuracaoService.getHoraFinalTransacao())){
			throw new TransacaoForaHorarioPermitidoException(configuracaoService.getHoraInicialTransacao(), configuracaoService.getHoraFinalTransacao());
		}
		
		return context.proceed();		
	}
}
