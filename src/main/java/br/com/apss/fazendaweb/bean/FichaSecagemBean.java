package br.com.apss.fazendaweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.apss.fazendaweb.enums.Genero;
import br.com.apss.fazendaweb.model.Animal;
import br.com.apss.fazendaweb.model.Categoria;
import br.com.apss.fazendaweb.model.CondicaoCorporal;
import br.com.apss.fazendaweb.model.FichaAnimal;
import br.com.apss.fazendaweb.model.Pessoa;
import br.com.apss.fazendaweb.model.Raca;
import br.com.apss.fazendaweb.model.TipoSecagem;
import br.com.apss.fazendaweb.service.AnimalService;
import br.com.apss.fazendaweb.service.CategoriaService;
import br.com.apss.fazendaweb.service.CondicaoCorporalService;
import br.com.apss.fazendaweb.service.FichaAnimalService;
import br.com.apss.fazendaweb.service.PessoaService;
import br.com.apss.fazendaweb.service.RacaService;
import br.com.apss.fazendaweb.service.TipoSecagemService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class FichaSecagemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private FichaAnimal secagem;
	private List<FichaAnimal> secagems = new ArrayList<>();
	private FichaAnimal secagemSelecionado;
	private Boolean edicao = false;
	private Animal animal;
	private Date dateMinima;

	@Inject
	FichaAnimalService secagemService;

	@Inject
	AnimalService animalService;

	@Inject
	PessoaService profissionalService;

	@Inject
	CondicaoCorporalService condicaoCorporalService;

	@Inject
	RacaService racaService;

	@Inject
	CategoriaService categoriaService;

	@Inject
	TipoSecagemService tipoSecagemService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando FichaSecagemBean...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}

	}

	private void carregarTabela() {
		secagem = new FichaAnimal();
		List<FichaAnimal> sc = new ArrayList<FichaAnimal>();
		this.secagems = secagemService.carregarFichaSecagem();
		/*
		 * for (FichaAnimal f : sc) { Date d1 = f.getDtParto(); Date d2 =
		 * f.getDtSecagem(); Long dias =(d2.getTime() - d1.getTime() + 3600000L)
		 * / 86400000L; f.setLactacao(dias); this.secagems.add(f); }
		 */
	}

	public void ajustaData() {
		this.dateMinima = this.secagem.getDtParto();
	}

	public List<FichaAnimal> getAnimals() {
		return secagemService.buscarFichaSecagem();
	}

	public List<TipoSecagem> getTipoSecagems() {
		return tipoSecagemService.listarTodos();
	}

	public List<Genero> getGeneros() {
		return Arrays.asList(Genero.values());
	}

	public List<Animal> getReprodutores() {
		return animalService.buscarAnimalReprodutor();
	}

	public List<CondicaoCorporal> getCondicoesCorporal() {
		return condicaoCorporalService.listarTodos(true);
	}

	public List<Raca> getRacas() {
		return racaService.listarTodos();
	}

	public List<Categoria> getCategorias() {
		return categoriaService.listarTodos();
	}

	public List<Pessoa> getResponsaveis() {
		return profissionalService.listarProfissional(true);
	}

	public void novoCadastro() {
		secagem = new FichaAnimal();
		edicao = false;
		animal = new Animal();
	}

	public void salvar() {
		secagemService.salvar(this.secagem);
		this.secagemSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
		RequestContext request = RequestContext.getCurrentInstance();
		request.addCallbackParam("sucesso", true);
		this.setEdicao(false);
	}

	public void carregarFichaSelecionada() {
		this.secagem = secagemService.buscarPorId(this.secagem.getId());
		ajustaData();
	}

	public void excluir() {
		limparCampos();
		secagemService.salvar(this.secagem);
		this.secagemSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	private void limparCampos() {
		this.secagem.setDtSecagem(null);
		this.secagem.setObsSecagem(null);
		this.secagem.setTipoSecagem(null);
	}

	public Boolean condicaoEditarExcluir() {
		editar();
		return !(this.getSecagemSelecionado() != null && this.secagem.getDtSecagem() == null);
	}

	public void infoDtSecagemValidation() {
		editar();
		if (!(this.getSecagemSelecionado() != null && this.secagem.getDtSecagem() == null)) {
			Messages.addGlobalWarn("Registro bloqueado para edição e exclusão por possuir secagem cadastrado.");
		}
	}

	public void editar() {
		if (null != secagemSelecionado) {
			this.secagem = secagemService.buscarPorId(secagemSelecionado.getId());
			this.edicao = true;
		}
	}

	public void calcularDias() {
		Date d1 = this.secagem.getDtParto();
		Date d2 = this.secagem.getDtSecagem();
		this.secagem.setLactacao((d1.getTime() - d2.getTime() + 3600000L) / 86400000L);
	}

	public FichaAnimal getSecagem() {
		return secagem;
	}

	public void setSecagem(FichaAnimal secagem) {
		this.secagem = secagem;
	}

	public List<FichaAnimal> getSecagems() {
		return secagems;
	}

	public void setSecagems(List<FichaAnimal> secagems) {
		this.secagems = secagems;
	}

	public FichaAnimal getSecagemSelecionado() {
		return secagemSelecionado;
	}

	public void setSecagemSelecionado(FichaAnimal secagemSelecionado) {
		this.secagemSelecionado = secagemSelecionado;
	}

	public Boolean getEdicao() {
		return edicao;
	}

	public void setEdicao(Boolean edicao) {
		this.edicao = edicao;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Date getDateMinima() {
		return dateMinima;
	}

	public void setDateMinima(Date dateMinima) {
		this.dateMinima = dateMinima;
	}
	
	

	/****************************** Getters e Setters *************************/

}
