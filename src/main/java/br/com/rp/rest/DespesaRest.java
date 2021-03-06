package br.com.rp.rest;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.dto.MovimentacaoDTO;
import br.com.rp.seguranca.InterceptorDespesa;
import br.com.rp.seguranca.Token;
import br.com.rp.services.MovimentacaoService;

@Path("/movimentacoes")
public class DespesaRest {

	@EJB
	private MovimentacaoService service;
	
	@POST
	@Path("/transferenciaVBank")
	@Interceptors(value = InterceptorDespesa.class)
	public Response realizarTransferenciaVBank(@HeaderParam(value = "token") Token token, MovimentacaoDTO movimentacao){
		service.realizarTransferenciaEntreContasVBank(movimentacao.getContaId(), movimentacao.getValor(), movimentacao.getContaDestinoId());
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/transferencia")
	@Interceptors(value = InterceptorDespesa.class)
	public Response realizarTransferencia(@HeaderParam(value = "token") Token token, MovimentacaoDTO movimentacao){
		service.realizarTransferencia(movimentacao.getContaId(), movimentacao.getValor(), movimentacao.getCodigoBancoDestino(), movimentacao.getNumeroContaDestino());
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/pagamento")
	@Interceptors(value = InterceptorDespesa.class)
	public Response realizarPagamento(@HeaderParam(value = "token") Token token, MovimentacaoDTO movimentacao){
		service.realizarPagamento(movimentacao.getContaId(), movimentacao.getPagamentoId());
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/deposito")
	@Interceptors(value = InterceptorDespesa.class)
	public Response realizarDeposito(@HeaderParam(value = "token") Token token, MovimentacaoDTO movimentacao){
		service.realizarDeposito(movimentacao.getContaId(), movimentacao.getValor(), movimentacao.getCmc7());
		return Response.status(Status.OK).build();
	}
}