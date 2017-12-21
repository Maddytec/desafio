package br.com.maddytec.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.maddytec.pedidovenda.model.Atributo;
import br.com.maddytec.pedidovenda.service.CadastroAtributoService;
import br.com.maddytec.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAtributoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroAtributoService cadastroAtributoService;

	private Atributo atributo;

	public CadastroAtributoBean() {
		limpar();
	}

	private void limpar() {
		atributo = new Atributo();
	}

	public void inicializar() {
		System.out.println("Inicializando...");

		if (this.atributo == null) {
			limpar();
		}
	}

	public void salvar() {
		this.atributo = cadastroAtributoService.salvar(this.atributo);
		limpar();

		FacesUtil.addInfoMessage("Atributo salvo com sucesso!");
	}

	public boolean isEditando() {
		return this.atributo.getId() != null;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

}
