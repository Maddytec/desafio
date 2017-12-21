package br.com.maddytec.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.maddytec.pedidovenda.model.Categoria;
import br.com.maddytec.pedidovenda.repository.Categorias;
import br.com.maddytec.pedidovenda.util.jpa.Transactional;

public class CadastroCategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	@Transactional
	public Categoria salvar(Categoria categoria) {
		
		return categorias.guardar(categoria);

	}
	
	
	public List<Categoria> getAllCategorias(){
		return categorias.lista();
	}
}
