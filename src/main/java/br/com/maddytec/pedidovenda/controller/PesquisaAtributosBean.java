package br.com.maddytec.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import br.com.maddytec.pedidovenda.model.Atributo;
import br.com.maddytec.pedidovenda.repository.Atributos;
import br.com.maddytec.pedidovenda.repository.filter.AtributoFilter;
import br.com.maddytec.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAtributosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Atributos atributos;

	private AtributoFilter filtro;

	private List<Atributo> atributosFiltrados;

	private Atributo atributoSelecionado;

	public PesquisaAtributosBean() {
		filtro = new AtributoFilter();
	}

	public void pesquisar() {
		atributosFiltrados = atributos.filtrados(filtro);
	}

	public void excluir() {
		if (atributoSelecionado != null) {
			try {
				atributos.remover(atributoSelecionado);
				atributosFiltrados.remove(atributoSelecionado);

				FacesUtil.addInfoMessage("Atributo " + atributoSelecionado.getNome()
						+ " foi excluído com sucesso.");
			} catch (PersistenceException e) {
				FacesUtil.addErrorMessage("O atributo "
						+ atributoSelecionado.getNome()
						+ " não pode ser excluído.");
			}
		}
	}

	public List<Atributo> getAtributosFiltrados() {
		return atributosFiltrados;
	}

	public AtributoFilter getFiltro() {
		return filtro;
	}

	public Atributo getAtributoSelecionado() {
		return atributoSelecionado;
	}

	public void setAtributoSelecionado(Atributo atributoSelecionado) {
		this.atributoSelecionado = atributoSelecionado;
	}

}