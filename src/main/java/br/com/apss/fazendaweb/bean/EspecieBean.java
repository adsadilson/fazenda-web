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
import br.com.apss.fazendaweb.model.Especie;
import br.com.apss.fazendaweb.service.EspecieService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class EspecieBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Especie especie;
	private List<Especie> especies = new ArrayList<>();
	private Especie especieSelecionado;

	@Inject
	EspecieService especieService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		especies = especieService.listarTodos();
	}

	public EspecieBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.especie = new Especie();
		this.especie.setStatus(true);
	}

	public void salvar() {
		especieService.salvar(this.especie);
		this.especieSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		especieService.remover(this.especie);
		this.especieSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.especie = especieService.buscarPorId(especieSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}

	public Especie getEspecieSelecionado() {
		return especieSelecionado;
	}

	public void setEspecieSelecionado(Especie especieSelecionado) {
		this.especieSelecionado = especieSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
