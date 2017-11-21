package br.com.apss.fazendaweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.apss.fazendaweb.model.Animal;
import br.com.apss.fazendaweb.model.CondicaoCorporal;
import br.com.apss.fazendaweb.model.FichaAnimal;
import br.com.apss.fazendaweb.model.Pessoa;
import br.com.apss.fazendaweb.service.AnimalService;
import br.com.apss.fazendaweb.service.CondicaoCorporalService;
import br.com.apss.fazendaweb.service.FichaAnimalService;
import br.com.apss.fazendaweb.service.PessoaService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
@ManagedBean
public class FichaCoberturaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private FichaAnimal cobertura;
	private List<FichaAnimal> coberturas = new ArrayList<>();
	private FichaAnimal coberturaSelecionado;

	@Inject
	FichaAnimalService coberturaService;

	@Inject
	AnimalService animalService;

	@Inject
	PessoaService profissionalService;

	@Inject
	CondicaoCorporalService condicaoCorporalService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}

	}

	public Boolean condicaoEditarExcluir() {
		editar();
		return !(this.getCoberturaSelecionado() != null && this.cobertura.getDtParto() == null);
	}

	public void infoDtPartoValidation() {
		editar();
		if (!(this.getCoberturaSelecionado() != null && this.cobertura.getDtParto() == null)) {
			Messages.addGlobalWarn("Registro bloqueado para edição e exclusão por possuir parto cadastrado.");
		}
	}

	private void carregarTabela() {
		cobertura = new FichaAnimal();
		coberturas = coberturaService.porTipoLanc("COBERTURA/DIAGNOSTICO");
	}

	public List<Animal> getAnimals() {
		return animalService.buscarAnimalCobertura();
	}

	public boolean verificarCobertura() {
		return coberturaService.verificaCobertura(this.cobertura.getAnimal(),
				this.cobertura.getId() != null ? true : false, this.cobertura);
	}

	public List<Animal> getReprodutores() {
		return animalService.buscarAnimalReprodutor();
	}

	public List<CondicaoCorporal> getCondicoesCorporal() {
		return condicaoCorporalService.listarTodos(true);
	}

	public List<Pessoa> getResponsaveis() {
		return profissionalService.listarProfissional(true);
	}

	public void novoCadastro() {
		cobertura = new FichaAnimal();
	}

	public void salvar() {
		if (!verificarCobertura()) {
			this.cobertura.setDtLanc(new Date());
			this.cobertura.setTipoLanc("COBERTURA/DIAGNOSTICO");
			coberturaService.salvar(this.cobertura);
			this.coberturaSelecionado = null;
			carregarTabela();
			Messages.addGlobalInfo("Registro salvo com sucesso");
			RequestContext request = RequestContext.getCurrentInstance();
			request.addCallbackParam("sucesso", true);
		}
	}

	public void excluir() {
		coberturaService.remover(this.cobertura);
		this.coberturaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		if (null != coberturaSelecionado) {
			this.cobertura = coberturaService.buscarPorId(coberturaSelecionado.getId());
		}
	}

	public FichaAnimal getCobertura() {
		return cobertura;
	}

	public void setCobertura(FichaAnimal cobertura) {
		this.cobertura = cobertura;
	}

	public List<FichaAnimal> getCoberturas() {
		return coberturas;
	}

	public void setCoberturas(List<FichaAnimal> coberturas) {
		this.coberturas = coberturas;
	}

	public FichaAnimal getCoberturaSelecionado() {
		return coberturaSelecionado;
	}

	public void setCoberturaSelecionado(FichaAnimal coberturaSelecionado) {
		this.coberturaSelecionado = coberturaSelecionado;
	}

	/****************************** Getters e Setters *************************/

}
