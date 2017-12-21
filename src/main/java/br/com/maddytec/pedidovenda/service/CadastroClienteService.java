package br.com.maddytec.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;


import br.com.maddytec.pedidovenda.model.Cliente;
import br.com.maddytec.pedidovenda.repository.Clientes;
import br.com.maddytec.pedidovenda.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		 
		Cliente clienteExistente = clientes.porEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe E-mail informado.");
		}
		
		return clientes.guardar(cliente);

	}

}
