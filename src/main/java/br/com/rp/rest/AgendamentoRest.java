package br.com.rp.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.domain.Agendamento;
import br.com.rp.services.AgendamentoService;

@Path("/agendamentos")
@Produces("application/json")
public class AgendamentoRest {

	@EJB
	private AgendamentoService agendamentoService;
	
	@POST
	@Consumes("application/json")
	public Response registrarAgendamento(Agendamento agendamento) {
		Boolean result = this.agendamentoService.agendarPagamento(agendamento);
		if(result)
			return Response.status(Status.CREATED).entity(agendamento).build();

		return Response.status(Status.BAD_REQUEST).entity("Agendamento Invalido.").build();
	}
	
	@PUT
	public Response cancelarAgendamento(Long idAgendamento) {
		Boolean result = this.agendamentoService.cancelarPagamentoPorID(idAgendamento);
		if(result)
			return Response.status(Status.OK).build();
		
		return Response.status(Status.BAD_REQUEST).build();
	}
}
