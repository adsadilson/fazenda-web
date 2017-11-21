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
import br.com.apss.fazendaweb.model.Animal;
import br.com.apss.fazendaweb.model.Pessoa;
import br.com.apss.fazendaweb.model.TipoVacina;
import br.com.apss.fazendaweb.model.Vacina;
import br.com.apss.fazendaweb.service.AnimalService;
import br.com.apss.fazendaweb.service.PessoaService;
import br.com.apss.fazendaweb.service.TipoVacinaService;
import br.com.apss.fazendaweb.service.VacinaService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class VacinaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Vacina vacina;
	private List<Vacina> vacinas = new ArrayList<>();
	private Vacina vacinaSelecionado;

	@Inject
	VacinaService vacinaService;

	
	@Inject
	AnimalService animalServic;
	
	@Inject
	TipoVacinaService tipoVacinaService;
	
	@Inject
	PessoaService profissionalService;
	
	
	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		vacinas = vacinaService.listarTodos();
	}

	public VacinaBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.vacina = new Vacina();
	}

	public void salvar() {
		vacinaService.salvar(this.vacina);
		this.vacinaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
		RequestContext request = RequestContext.getCurrentInstance();
		request.addCallbackParam("sucesso", true);
	}

	public void excluir() {
		vacinaService.remover(this.vacina);
		this.vacinaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.vacina = vacinaService.buscarPorId(vacinaSelecionado.getId());
	}
	
	public List<Animal> getAnimals() {
		return animalServic.listarTodos();
	}
	
	public List<TipoVacina> getTipoVacinas() {
		return tipoVacinaService.listarTodos();
	}
	
	public List<Pessoa> getResponsaveis() {
		return profissionalService.listarProfissional(true);
	}

	/****************************** Getters e Setters *************************/

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	public Vacina getVacinaSelecionado() {
		return vacinaSelecionado;
	}

	public void setVacinaSelecionado(Vacina vacinaSelecionado) {
		this.vacinaSelecionado = vacinaSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
