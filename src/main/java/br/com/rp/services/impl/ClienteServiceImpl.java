package br.com.rp.services.impl;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Cpf;
import br.com.rp.repository.ClienteRepository;
import br.com.rp.services.ClienteService;

@Stateless
public class ClienteServiceImpl implements ClienteService{
	
	@EJB
	private ClienteRepository clienteRepository;
	
	
	@Override
	public boolean realizarTransferencia(Long clienteId, Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {
		
		
		
		return false;
	}

	@Override
	public boolean atualizarDados(Cliente cliente) {

		Cliente clienteBanco = clienteRepository.findById(cliente.getId());
		
		clienteBanco.setEndereco(cliente.getEndereco());
		clienteBanco.setEmail(cliente.getEmail());
		
		clienteRepository.save(clienteBanco);
				
		return false;
	}

	@Override
	public Cliente buscarClientePorCpf(Cpf cpf) {

		Cliente cliente = clienteRepository.findClienteByCpf(cpf);
		
		return cliente;
	}

}
