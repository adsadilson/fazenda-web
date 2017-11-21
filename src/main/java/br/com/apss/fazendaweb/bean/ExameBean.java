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
import org.primefaces.context.RequestContext;

import br.com.apss.fazendaweb.enums.AtivoInativo;
import br.com.apss.fazendaweb.enums.ResultadoExame;
import br.com.apss.fazendaweb.model.Animal;
import br.com.apss.fazendaweb.model.Exame;
import br.com.apss.fazendaweb.model.TipoExame;
import br.com.apss.fazendaweb.service.AnimalService;
import br.com.apss.fazendaweb.service.ExameService;
import br.com.apss.fazendaweb.service.TipoExameService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class ExameBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Exame exame;
	private List<Exame> exames = new ArrayList<>();
	private Exame exameSelecionado;

	@Inject
	ExameService exameService;

	@Inject
	AnimalService animalServic;

	@Inject
	TipoExameService tipoExameService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		exames = exameService.listarTodos();
	}

	public ExameBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.exame = new Exame();
	}

	public void salvar() {
		exameService.salvar(this.exame);
		this.exameSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
		RequestContext request = RequestContext.getCurrentInstance();
		request.addCallbackParam("sucesso", true);
	}

	public void excluir() {
		exameService.remover(this.exame);
		this.exameSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.exame = exameService.buscarPorId(exameSelecionado.getId());
	}

	public List<Animal> getAnimals() {
		return animalServic.listarTodos();
	}

	public List<TipoExame> getTipoExames() {
		return tipoExameService.listarTodos();
	}

	public List<ResultadoExame> getResultados() {
		return Arrays.asList(ResultadoExame.values());
	}

	/****************************** Getters e Setters *************************/

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public Exame getExameSelecionado() {
		return exameSelecionado;
	}

	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
