package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Cliente;
import br.com.rp.repository.ClienteRepository;

@Stateless
public class ClienteRepositoryImpl extends AbstractRepositoryImpl<Cliente> implements ClienteRepository {

	public ClienteRepositoryImpl() {
		super(Cliente.class);
	}
}
