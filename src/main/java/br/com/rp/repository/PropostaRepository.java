package br.com.rp.repository;


import java.util.List;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Proposta;


public interface PropostaRepository extends Repository<Proposta> {
	
	List<Proposta> procurarPorPropostasComMenosDe30DiasDoCliente(Cliente cliente);

	Boolean verificarSeOClienteJaEstaAtivo(Cliente cliente);
	
	List<Proposta> procurarProspostasPorEstado(String estado);
}
