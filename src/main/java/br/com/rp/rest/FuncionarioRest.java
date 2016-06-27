package br.com.rp.rest;

import javax.ejb.EJB;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rp.domain.Cargo;
import br.com.rp.dto.ConfiguracaoDTO;
import br.com.rp.seguranca.Token;
import br.com.rp.services.GerenteService;

@Path("/funcioarios")
public class FuncionarioRest {

	@EJB
	GerenteService gerenteService;
	
	
	@POST
	@Path("/alterarHorarioTransacao")
//	@Interceptors(value = PRECISA_CRIAR_O_INTERCPTOR.class)
	public Response alterarHorarioTransacao(@HeaderParam(value = "token") Token token, ConfiguracaoDTO configuracao){
		
		if (token != token.GERENTE_SEGURANCA) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		gerenteService.alterarHorarioTransacao(Cargo.GERENTE_SEGURANCA, configuracao.getHoraInicialTransacao(), configuracao.getHoraFinalTransacao());
		return Response.status(Status.OK).build();
	}
	
	
}
