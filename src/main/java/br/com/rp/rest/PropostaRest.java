package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.domain.Proposta;
import br.com.rp.seguranca.InterceptorProposta;
import br.com.rp.seguranca.Token;
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
	
	@GET
	@Path(value="/lista/{estado}")
	@Interceptors(value = InterceptorProposta.class)
	public List<Proposta> procurarProposta(@HeaderParam(value = "token") Token token, @PathParam(value="estado") String estado){
		return propostaService.pesquisarPropostasPorEstado(estado.toUpperCase());
	}
	
	@POST
	@Path(value="/aceitar/{id}")
	@Interceptors(value = InterceptorProposta.class)
	public Response aceitarProposta(@HeaderParam(value = "token") Token token, @PathParam("id") Long id){		 
		propostaService.aceitarProposta(id);
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path(value="/rejeitar")
	@Interceptors(value = InterceptorProposta.class)
	public Response recusarProposta(@HeaderParam(value = "token") Token token, Proposta proposta) {
		propostaService.rejeitarProposta(proposta.getId(), proposta.getFuncionario().getId(), proposta.getMotivoRejeicao());
		return Response.status(Status.OK).build();
	}
}