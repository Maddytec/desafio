package br.com.maddytec.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.maddytec.pedidovenda.model.Variacao;
import br.com.maddytec.pedidovenda.repository.Variacoes;
import br.com.maddytec.pedidovenda.util.jpa.Transactional;

public class CadastroVariacaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Variacoes variacoes;

	@Transactional
	public Variacao salvar(Variacao variacao) {
		Variacao variacaoExistente = variacoes.porNome(variacao.getNome());

		if (variacaoExistente != null && !variacaoExistente.equals(variacao)) {
			throw new NegocioException("Já existe a variacao informado.");
		}

		return variacoes.guardar(variacao);

	}
	
	
	public List<Variacao> getAllVariacoes(){
		return variacoes.lista();
	}
	

}
