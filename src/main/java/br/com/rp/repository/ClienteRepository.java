package br.com.rp.repository;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Cpf;

public interface ClienteRepository extends Repository<Cliente> {
	
	public Cliente findClienteByCpf(Cpf cpf);

}
