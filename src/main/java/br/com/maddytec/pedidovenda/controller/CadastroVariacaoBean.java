package br.com.maddytec.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.maddytec.pedidovenda.model.Atributo;
import br.com.maddytec.pedidovenda.model.Variacao;
import br.com.maddytec.pedidovenda.repository.Atributos;
import br.com.maddytec.pedidovenda.service.CadastroVariacaoService;
import br.com.maddytec.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroVariacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroVariacaoService cadastroVariacaoService;
	
	@Inject
	private Atributos atributos;

	private Variacao variacao;
	
	private Atributo atributo;
	private List<Atributo> listAtributos;
	
	
	public CadastroVariacaoBean() {
		limpar();
	}

	private void limpar() {
		variacao = new Variacao();
	}

	public void inicializar() {
		System.out.println("Inicializando...");

		if(this.variacao == null) {
			limpar();
		}
		
		this.listAtributos = atributos.lista();
		
	}

	public void salvar() {
		this.variacao = cadastroVariacaoService.salvar(this.variacao);
		limpar();

		FacesUtil.addInfoMessage("Variacao salvo com sucesso!");
	}

	public boolean isEditando() {
		return this.variacao.getId() != null;
	}

	public Variacao getVariacao() {
		return variacao;
	}

	public void setVariacao(Variacao variacao) {
		this.variacao = variacao;
	}

	public List<Atributo> getListAtributos() {
		return listAtributos;
	}

	public void setListAtributos(List<Atributo> listAtributos) {
		this.listAtributos = listAtributos;
	}

	public Atributos getAtributos() {
		return atributos;
	}

	public void setAtributos(Atributos atributos) {
		this.atributos = atributos;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}
	
	
	
}
