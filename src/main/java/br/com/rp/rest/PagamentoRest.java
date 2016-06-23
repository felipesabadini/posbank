package br.com.rp.rest;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.domain.Pagamento;
import br.com.rp.services.PagamentoService;

@Path("/pagamentos")
@Produces("application/json")
public class PagamentoRest {

	@EJB
	private PagamentoService pagamentoService;
	
	public Response registrarNovoPagamento(Pagamento pagamento) {
		Boolean result = this.pagamentoService.registrarNovoPagamento(pagamento);
		if(result)
			return Response.status(Status.OK).build();
		
		return Response.status(Status.BAD_REQUEST).build();		
	}
}
