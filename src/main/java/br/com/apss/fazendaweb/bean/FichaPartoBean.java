package br.com.apss.fazendaweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import br.com.apss.fazendaweb.model.TipoParto;
import br.com.apss.fazendaweb.service.AnimalService;
import br.com.apss.fazendaweb.service.CategoriaService;
import br.com.apss.fazendaweb.service.CondicaoCorporalService;
import br.com.apss.fazendaweb.service.FichaAnimalService;
import br.com.apss.fazendaweb.service.PessoaService;
import br.com.apss.fazendaweb.service.RacaService;
import br.com.apss.fazendaweb.service.TipoPartoService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class FichaPartoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private FichaAnimal parto;
	private List<FichaAnimal> partos = new ArrayList<>();
	private FichaAnimal partoSelecionado;
	private Boolean edicao = false;
	private Animal animal;

	@Inject
	FichaAnimalService partoService;

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
	TipoPartoService tipoPartoService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando FichaPartoBean...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}

	}

	private void carregarTabela() {
		parto = new FichaAnimal();
		partos = partoService.carregarFichaParto();
	}

	public List<FichaAnimal> getAnimals() {
		return partoService.buscarPraParto();
	}

	public List<TipoParto> getTipoPartos() {
		return tipoPartoService.listarTodos();
	}

	public List<Genero> getGeneros() {
		return Arrays.asList(Genero.values());
	}

	public void qtdes() {
		if (this.parto.getTipoParto().getNome().contains("ABORTO")) {
			this.parto.setQuantidade(0);
			this.parto.setBrincoCria1(null);
			this.parto.setNomeCria1(null);
			this.parto.setGeneroCria1(null);
			this.parto.setRaca1(null);
			this.parto.setCategoria1(null);
			this.parto.setPeso1(null);
			this.parto.setBrincoCria2(null);
			this.parto.setNomeCria2(null);
			this.parto.setGeneroCria2(null);
			this.parto.setRaca2(null);
			this.parto.setCategoria2(null);
			this.parto.setPeso2(null);
		} else {
			this.parto.setQuantidade(1);
		}
	}

	/*
	 * public boolean verificarParto() { BigDecimal valor1 =
	 * this.parto.getQuantidade(); BigDecimal valor2 = new BigDecimal("1");
	 * 
	 * int res = valor1.compareTo(valor2); if (res == 0) { if
	 * (this.parto.getBrincoCria1().isEmpty() ||
	 * this.parto.getNomeCria1().isEmpty() || this.parto.getPeso1().isEmpty() ||
	 * this.parto.getGeneroCria1().isEmpty() || this.parto.getCategoria1() ==
	 * null || this.parto.getRaca1() == null) {
	 * Messages.addGlobalError("Informe todos os campos aba Cria 1"); return
	 * true; } else { return false; } } else if (res == 1) {
	 * Messages.addGlobalError("Informe todos os campos aba Cria 1 e Cria 2 ");
	 * return true; } return false; }
	 */

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
		parto = new FichaAnimal();
		edicao = false;
		animal = new Animal();
	}

	public void salvar() {
		partoService.salvar(this.parto);
		this.partoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
		RequestContext request = RequestContext.getCurrentInstance();
		request.addCallbackParam("sucesso", true);
		this.setEdicao(false);
	}

	public void teste() {
		this.parto = partoService.buscarPorId(this.animal.getId());
	}

	public void excluir() {
		limparCampos();
		partoService.salvar(this.parto);
		this.partoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	private void limparCampos() {
		this.parto.setDtParto(null);
		this.parto.setTipoParto(null);
		this.parto.setQuantidade(0);
		this.parto.setNomeCria1(null);
		this.parto.setBrincoCria1(null);
		this.parto.setGeneroCria1(null);
		this.parto.setPeso1(null);
		this.parto.setCategoria1(null);
		this.parto.setRaca1(null);
		this.parto.setNomeCria2(null);
		this.parto.setBrincoCria2(null);
		this.parto.setGeneroCria2(null);
		this.parto.setPeso2(null);
		this.parto.setCategoria2(null);
		this.parto.setRaca2(null);
		this.parto.setObsParto(null);
	}

	public void editar() {
		if (null != partoSelecionado) {
			this.edicao=true;
			this.parto = partoService.buscarPorId(partoSelecionado.getId());
		}
	}
	
	public Boolean condicaoEditarExcluir() {
		editar();
		return !(this.getPartoSelecionado() != null && this.parto.getDtSecagem() == null);
	}

	public void infoDtPartoValidation() {
		editar();
		if (!(this.getPartoSelecionado() != null && this.parto.getDtSecagem() == null)) {
			Messages.addGlobalWarn("Registro bloqueado para edição e exclusão por possuir secagem cadastrado.");
		}
	}

	public FichaAnimal getParto() {
		return parto;
	}

	public void setParto(FichaAnimal parto) {
		this.parto = parto;
	}

	public List<FichaAnimal> getPartos() {
		return partos;
	}

	public void setPartos(List<FichaAnimal> partos) {
		this.partos = partos;
	}

	public FichaAnimal getPartoSelecionado() {
		return partoSelecionado;
	}

	public void setPartoSelecionado(FichaAnimal partoSelecionado) {
		this.partoSelecionado = partoSelecionado;
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

	/****************************** Getters e Setters *************************/

}
