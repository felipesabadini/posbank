package br.com.rp.repository;

import java.util.List;

import br.com.rp.domain.Conta;

public interface ContaRepository extends Repository<Conta> {
	
	public List<Conta> consultarContaPorClienteId(Long clienteId);
	
}