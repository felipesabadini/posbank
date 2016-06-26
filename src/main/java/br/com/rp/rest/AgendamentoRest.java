package br.com.rp.rest;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.domain.Agendamento;
import br.com.rp.seguranca.InterceptorAgendamento;
import br.com.rp.seguranca.Token;
import br.com.rp.services.AgendamentoService;

@Path("/agendamentos")
@Produces("application/json")
//@Consumes("application/json")
public class AgendamentoRest {

	@EJB
	private AgendamentoService agendamentoService;
	
	@POST
	@Consumes("application/json")
	@Interceptors(value = InterceptorAgendamento.class)
	public Response registrarAgendamento(@HeaderParam(value = "token") Token token, Agendamento agendamento) {
		Boolean result = this.agendamentoService.agendarPagamento(agendamento);
		if(result)
			return Response.status(Status.CREATED).entity(agendamento).build();

		return Response.status(Status.BAD_REQUEST).entity("Agendamento Invalido.").build();
	}
	
	@PUT
	@Interceptors(value = InterceptorAgendamento.class)
	public Response cancelarAgendamento(@HeaderParam(value = "token") Token token, Long idAgendamento) {
		Boolean result = this.agendamentoService.cancelarPagamentoPorID(idAgendamento);
		if(result)
			return Response.status(Status.OK).build();
		
		return Response.status(Status.BAD_REQUEST).build();
	}
}
