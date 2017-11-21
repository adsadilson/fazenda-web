package br.com.apss.fazendaweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.apss.fazendaweb.enums.AtivoInativo;
import br.com.apss.fazendaweb.model.GrupoProduto;
import br.com.apss.fazendaweb.service.GrupoProdutoService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class GrupoProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private GrupoProduto grupoProduto;
	private List<GrupoProduto> grupoProdutos = new ArrayList<>();
	private GrupoProduto grupoProdutoSelecionado;

	@Inject
	GrupoProdutoService grupoProdutoService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		grupoProdutos = grupoProdutoService.listarTodos();
	}

	public GrupoProdutoBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.grupoProduto = new GrupoProduto();
		this.grupoProduto.setStatus(true);
	}

	public void salvar() {
		grupoProdutoService.salvar(this.grupoProduto);
		this.grupoProdutoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		grupoProdutoService.remover(this.grupoProduto);
		this.grupoProdutoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.grupoProduto = grupoProdutoService.buscarPorId(grupoProdutoSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public List<GrupoProduto> getGrupoProdutos() {
		return grupoProdutos;
	}

	public void setGrupoProdutos(List<GrupoProduto> grupoProdutos) {
		this.grupoProdutos = grupoProdutos;
	}

	public GrupoProduto getGrupoProdutoSelecionado() {
		return grupoProdutoSelecionado;
	}

	public void setGrupoProdutoSelecionado(GrupoProduto grupoProdutoSelecionado) {
		this.grupoProdutoSelecionado = grupoProdutoSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
