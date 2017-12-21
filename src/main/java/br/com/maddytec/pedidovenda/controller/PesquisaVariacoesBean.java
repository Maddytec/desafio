package br.com.maddytec.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import br.com.maddytec.pedidovenda.model.Variacao;
import br.com.maddytec.pedidovenda.repository.Variacoes;
import br.com.maddytec.pedidovenda.repository.filter.VariacaoFilter;
import br.com.maddytec.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaVariacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Variacoes variacoes;

	private VariacaoFilter filtro;

	private List<Variacao> variacoesFiltrados;

	private Variacao variacaoSelecionado;

	public PesquisaVariacoesBean() {
		filtro = new VariacaoFilter();
	}

	public void pesquisar() {
		variacoesFiltrados = variacoes.filtrados(filtro);
	}

	public void excluir() {
		if (variacaoSelecionado != null) {
			try {
				variacoes.remover(variacaoSelecionado);
				variacoesFiltrados.remove(variacaoSelecionado);

				FacesUtil.addInfoMessage("Variacao " + variacaoSelecionado.getNome()
						+ " foi excluído com sucesso.");
			} catch (PersistenceException e) {
				FacesUtil.addErrorMessage("O variacao "
						+ variacaoSelecionado.getNome()
						+ " não pode ser excluído.");
			}
		}
	}

	public List<Variacao> getVariacoesFiltrados() {
		return variacoesFiltrados;
	}

	public VariacaoFilter getFiltro() {
		return filtro;
	}

	public Variacao getVariacaoSelecionado() {
		return variacaoSelecionado;
	}

	public void setVariacaoSelecionado(Variacao variacaoSelecionado) {
		this.variacaoSelecionado = variacaoSelecionado;
	}

}