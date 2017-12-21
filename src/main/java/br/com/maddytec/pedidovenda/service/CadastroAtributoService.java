package br.com.maddytec.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.maddytec.pedidovenda.model.Atributo;
import br.com.maddytec.pedidovenda.repository.Atributos;
import br.com.maddytec.pedidovenda.util.jpa.Transactional;

public class CadastroAtributoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Atributos atributos;

	@Transactional
	public Atributo salvar(Atributo atributo) {
		
		return atributos.guardar(atributo);

	}
	
	
	public List<Atributo> getAllAtributos(){
		return atributos.lista();
	}
}
