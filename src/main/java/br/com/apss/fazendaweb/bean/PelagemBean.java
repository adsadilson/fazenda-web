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
import br.com.apss.fazendaweb.model.Pelagem;
import br.com.apss.fazendaweb.service.PelagemService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class PelagemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pelagem pelagem;
	private List<Pelagem> pelagems = new ArrayList<>();
	private Pelagem pelagemSelecionado;

	@Inject
	PelagemService pelagemService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		pelagems = pelagemService.listarTodos();
	}

	public PelagemBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.pelagem = new Pelagem();
		this.pelagem.setStatus(true);
	}

	public void salvar() {
		pelagemService.salvar(this.pelagem);
		this.pelagemSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		pelagemService.remover(this.pelagem);
		this.pelagemSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.pelagem = pelagemService.buscarPorId(pelagemSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Pelagem getPelagem() {
		return pelagem;
	}

	public void setPelagem(Pelagem pelagem) {
		this.pelagem = pelagem;
	}

	public List<Pelagem> getPelagems() {
		return pelagems;
	}

	public void setPelagems(List<Pelagem> pelagems) {
		this.pelagems = pelagems;
	}

	public Pelagem getPelagemSelecionado() {
		return pelagemSelecionado;
	}

	public void setPelagemSelecionado(Pelagem pelagemSelecionado) {
		this.pelagemSelecionado = pelagemSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
