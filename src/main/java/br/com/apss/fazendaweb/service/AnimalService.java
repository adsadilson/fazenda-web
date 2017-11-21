package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Animal;
import br.com.apss.fazendaweb.repository.AnimalRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class AnimalService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AnimalRepository animalRepository;

	@Transactional
	public Animal salvar(Animal animal) {
		Animal animalExistente = animalRepository.porNome(animal.getNome());

		if (animalExistente != null && !animalExistente.equals(animal)) {
			throw new NegocioException("Já existe uma Animal com esse nome informado.");
		}
		return animalRepository.save(animal);
	}

	@Transactional
	public void remover(Animal animal) {
		animalRepository.remove(animal);
	}

	public Animal buscarPorId(Long id) {
		return animalRepository.porId(id);
	}

	public List<Animal> listarTodos() {
		return animalRepository.listarTodos();
	}

	public List<Animal> buscarAnimalCobertura() {
		return animalRepository.buscarAnimalCobertura();
	}
	
	public List<Animal> buscarAnimalReprodutor() {
		return animalRepository.buscarAnimalReprodutor();
	}

	public Animal porId(Long id) {
		return animalRepository.porId(id);
	}
	
	public List<Animal> buscarPraParto() {
		return animalRepository.buscarPraParto();
	}

}
