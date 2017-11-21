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
import br.com.apss.fazendaweb.enums.Genero;
import br.com.apss.fazendaweb.model.Animal;
import br.com.apss.fazendaweb.model.Categoria;
import br.com.apss.fazendaweb.model.Origem;
import br.com.apss.fazendaweb.model.Pelagem;
import br.com.apss.fazendaweb.model.Raca;
import br.com.apss.fazendaweb.service.AnimalService;
import br.com.apss.fazendaweb.service.CategoriaService;
import br.com.apss.fazendaweb.service.OrigemService;
import br.com.apss.fazendaweb.service.PelagemService;
import br.com.apss.fazendaweb.service.RacaService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class AnimalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Animal animal;
	private List<Animal> animals = new ArrayList<>();
	private Animal animalSelecionado;

	@Inject
	AnimalService animalService;
	
	@Inject
	RacaService racaService;
	
	@Inject
	OrigemService origemService;
	
	@Inject
	CategoriaService categoriaService;
	
	@Inject
	PelagemService pelagemService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("AnimalBean: Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		animal = new Animal();
		animals = animalService.listarTodos();
	}

	public AnimalBean() {
	}
	
	public List<Raca> getRacas(){
		return racaService.listarTodos();
	}
	
	public List<Origem> getOrigens(){
		return origemService.listarTodos();
	}
	
	public List<Categoria> getCategorias(){
		return categoriaService.listarTodos();
	}
	
	public List<Pelagem> getPelagens(){
		return pelagemService.listarTodos();
	}
	

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}
	
	public List<Genero> getGeneros() {
		return Arrays.asList(Genero.values());
	}

	public void novoCadastro() {
		this.animal = new Animal();
		this.animal.setStatus(true);
	}

	public void salvar() {
		animalService.salvar(this.animal);
		this.animalSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		animalService.remover(this.animal);
		this.animalSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.animal = animalService.buscarPorId(animalSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public Animal getAnimalSelecionado() {
		return animalSelecionado;
	}

	public void setAnimalSelecionado(Animal animalSelecionado) {
		this.animalSelecionado = animalSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
