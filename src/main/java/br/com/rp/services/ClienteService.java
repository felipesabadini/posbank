package br.com.rp.services;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Cpf;

public interface ClienteService {
	
	public boolean atualizarDados(Cliente cliente);
	
	public Cliente buscarClientePorCpf(Cpf cpf);

}
