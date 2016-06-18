package br.com.rp.rest;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.domain.Proposta;
import br.com.rp.services.PropostaService;

@Path("/propostas")
@Produces("application/json")
public class PropostaRest {
	
	@EJB
	private PropostaService propostaService;
	
	@POST
	public Response registrarProposta(Proposta proposta) {
		Proposta result = this.propostaService.processoParaRegistrarUmaProposta(proposta);		
		return Response.status(Status.CREATED).entity(result).build();
	}

}
