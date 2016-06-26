package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.domain.Cargo;
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
	
	@GET
	@Path(value="/lista/{estado}")
	public List<Proposta> procurarProposta(@PathParam(value="estado") String estado){
		return propostaService.pesquisarPropostasPorEstado(estado.toUpperCase());
	}
	
	@POST
	@Path(value="/aceitar/{id}")
	public Response aceitarProposta(@HeaderParam(value = "cargo") Cargo cargo, @PathParam("id") Long id){
		propostaService.aceitarProposta(id);
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path(value="/rejeitar")
	public Response recusarProposta(Proposta proposta){
		propostaService.rejeitarProposta(proposta.getId(), proposta.getFuncionario().getId(), proposta.getMotivoRejeicao());
		return Response.status(Status.OK).build();
	}
}