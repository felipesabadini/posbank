package br.com.rp.rest;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.dto.MovimentacaoDTO;
import br.com.rp.services.MovimentacaoService;

@Path("/movimentacoes")
public class DespesaRest {

	@EJB
	private MovimentacaoService service;
	
	@POST
	@Path("/transferenciaVBank")
	public Response realizarTransferenciaVBank(MovimentacaoDTO movimentacao){
		service.realizarTransferenciaEntreContasVBank(movimentacao.getContaId(), movimentacao.getValor(), movimentacao.getContaDestinoId());
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/transferencia")
	public Response realizarTransferencia(MovimentacaoDTO movimentacao){
		service.realizarTransferencia(movimentacao.getContaId(), movimentacao.getValor(), movimentacao.getCodigoBancoDestino(), movimentacao.getNumeroContaDestino());
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/pagamento")
	public Response realizarPagamento(MovimentacaoDTO movimentacao){
		service.realizarPagamento(movimentacao.getContaId(), movimentacao.getPagamentoId());
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/deposito")
	public Response realizarDeposito(MovimentacaoDTO movimentacao){
		service.realizarDeposito(movimentacao.getContaId(), movimentacao.getValor(), movimentacao.getCmc7());
		return Response.status(Status.OK).build();
	}
}