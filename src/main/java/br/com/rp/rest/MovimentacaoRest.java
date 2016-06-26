package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.domain.Despesa;
import br.com.rp.domain.Movimentacao;
import br.com.rp.dto.MovimentacaoDTO;
import br.com.rp.seguranca.InterceptorMovimentacaoClienteEGerenteDeContas;
import br.com.rp.seguranca.Token;
import br.com.rp.services.DespesaService;
import br.com.rp.services.MovimentacaoService;

@Path("/movimentacoes")
public class MovimentacaoRest {

	@EJB
	private MovimentacaoService service;
	@EJB
	private DespesaService despesaService;
	
	@GET
	@Path("/despesas/{cartaoId}")
	@Interceptors(value = InterceptorMovimentacaoClienteEGerenteDeContas.class)
	public List<Despesa> consultarDespesasPorCartao(@HeaderParam(value = "token") Token token, @PathParam("cartaoId") Long cartaoId){
		return despesaService.consultarDespesasPorCartaoId(cartaoId);
	}
	
	@GET
	@Path("/movimentacao/{contaId}")
	@Interceptors(value = InterceptorMovimentacaoClienteEGerenteDeContas.class)
	public List<Movimentacao> consultarMovimentacaoBancariaPorConta(@HeaderParam(value = "token") Token token, @PathParam("contaId") Long contaId){
		return service.consultarMovimentacaoPorContaId(contaId);
	}
	
	@POST
	@Path("/transferenciaVBank")
	@Interceptors(value = InterceptorMovimentacaoClienteEGerenteDeContas.class)
	public Response realizarTransferenciaVBank(@HeaderParam(value = "token") Token token, MovimentacaoDTO movimentacao){
		service.realizarTransferenciaEntreContasVBank(movimentacao.getContaId(), movimentacao.getValor(), movimentacao.getContaDestinoId());
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/transferencia")
	@Interceptors(value = InterceptorMovimentacaoClienteEGerenteDeContas.class)
	public Response realizarTransferencia(@HeaderParam(value = "token") Token token, MovimentacaoDTO movimentacao){
		service.realizarTransferencia(movimentacao.getContaId(), movimentacao.getValor(), movimentacao.getCodigoBancoDestino(), movimentacao.getNumeroContaDestino());
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/pagamento")
	@Interceptors(value = InterceptorMovimentacaoClienteEGerenteDeContas.class)
	public Response realizarPagamento(@HeaderParam(value = "token") Token token, MovimentacaoDTO movimentacao){
		service.realizarPagamento(movimentacao.getContaId(), movimentacao.getPagamentoId());
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/deposito")
	@Interceptors(value = InterceptorMovimentacaoClienteEGerenteDeContas.class)
	public Response realizarDeposito(@HeaderParam(value = "token") Token token, MovimentacaoDTO movimentacao){
		service.realizarDeposito(movimentacao.getContaId(), movimentacao.getValor(), movimentacao.getCmc7());
		return Response.status(Status.OK).build();
	}
}