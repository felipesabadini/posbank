package br.com.rp.seguranca;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class InterceptorAgendamento {

	@AroundInvoke
	public Object verificarPermissao(InvocationContext context) throws Exception {
		Object[] params = context.getParameters();
		Token token = (Token) params[0];
		if(token.equals(Token.GERENTE_DE_OPERACOES)) {
			throw new AcessoNaoPermitidoException();
		}
		
		return context.proceed();		
	}
}
