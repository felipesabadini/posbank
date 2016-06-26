package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.domain.Despesa;
import br.com.rp.domain.Movimentacao;
import br.com.rp.dto.MovimentacaoDTO;
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
	@Produces("application/json")
	public List<Despesa> consultarDespesasPorCartao(@PathParam("cartaoId") Long cartaoId){
		return despesaService.consultarDespesasPorCartaoId(cartaoId);
	}
	
	@GET
	@Path("/movimentacoes/{contaId}")
	@Produces("application/json")
	public List<Movimentacao> consultarMovimentacaoBancariaPorConta(@PathParam("contaId") Long contaId){
		return service.consultarMovimentacaoPorContaId(contaId);
	}
	
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