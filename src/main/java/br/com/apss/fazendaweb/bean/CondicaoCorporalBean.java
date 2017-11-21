package br.com.apss.fazendaweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.apss.fazendaweb.enums.AtivoInativo;
import br.com.apss.fazendaweb.model.CondicaoCorporal;
import br.com.apss.fazendaweb.service.CondicaoCorporalService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class CondicaoCorporalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private CondicaoCorporal condicaoCorporal;
	private List<CondicaoCorporal> condicaoCorporals = new ArrayList<>();
	private CondicaoCorporal condicaoCorporalSelecionado;

	@Inject
	CondicaoCorporalService condicaoCorporalService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		condicaoCorporals = condicaoCorporalService.listarTodos();
	}

	public CondicaoCorporalBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.condicaoCorporal = new CondicaoCorporal();
		this.condicaoCorporal.setStatus(true);
	}

	public void salvar() {
		condicaoCorporalService.salvar(this.condicaoCorporal);
		this.condicaoCorporalSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		condicaoCorporalService.remover(this.condicaoCorporal);
		this.condicaoCorporalSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.condicaoCorporal = condicaoCorporalService.buscarPorId(condicaoCorporalSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public CondicaoCorporal getCondicaoCorporal() {
		return condicaoCorporal;
	}

	public void setCondicaoCorporal(CondicaoCorporal condicaoCorporal) {
		this.condicaoCorporal = condicaoCorporal;
	}

	public List<CondicaoCorporal> getCondicaoCorporals() {
		return condicaoCorporals;
	}

	public void setCondicaoCorporals(List<CondicaoCorporal> condicaoCorporals) {
		this.condicaoCorporals = condicaoCorporals;
	}

	public CondicaoCorporal getCondicaoCorporalSelecionado() {
		return condicaoCorporalSelecionado;
	}

	public void setCondicaoCorporalSelecionado(CondicaoCorporal condicaoCorporalSelecionado) {
		this.condicaoCorporalSelecionado = condicaoCorporalSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
