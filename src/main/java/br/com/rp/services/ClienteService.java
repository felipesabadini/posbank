package br.com.rp.services;

import java.math.BigDecimal;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Cpf;

public interface ClienteService {
	
	public boolean realizarTransferencia(Long clienteId, Long contaOrigemId, Long contaDestinoId, BigDecimal valor );
	
	public boolean atualizarDados(Cliente cliente);
	
	public Cliente buscarClientePorCpf(Cpf cpf);

}
