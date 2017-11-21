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
import br.com.apss.fazendaweb.model.UnidadeMedida;
import br.com.apss.fazendaweb.service.UnidadeMedidaService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class UnidadeMedidaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UnidadeMedida unidadeMedida;
	private List<UnidadeMedida> unidadeMedidas = new ArrayList<>();
	private UnidadeMedida unidadeMedidaSelecionado;

	@Inject
	UnidadeMedidaService unidadeMedidaService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		unidadeMedidas = unidadeMedidaService.listarTodos();
	}

	public UnidadeMedidaBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.unidadeMedida = new UnidadeMedida();
		this.unidadeMedida.setStatus(true);
	}

	public void salvar() {
		unidadeMedidaService.salvar(this.unidadeMedida);
		this.unidadeMedidaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		unidadeMedidaService.remover(this.unidadeMedida);
		this.unidadeMedidaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.unidadeMedida = unidadeMedidaService.buscarPorId(unidadeMedidaSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public List<UnidadeMedida> getUnidadeMedidas() {
		return unidadeMedidas;
	}

	public void setUnidadeMedidas(List<UnidadeMedida> unidadeMedidas) {
		this.unidadeMedidas = unidadeMedidas;
	}

	public UnidadeMedida getUnidadeMedidaSelecionado() {
		return unidadeMedidaSelecionado;
	}

	public void setUnidadeMedidaSelecionado(UnidadeMedida unidadeMedidaSelecionado) {
		this.unidadeMedidaSelecionado = unidadeMedidaSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
