package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Raca;
import br.com.apss.fazendaweb.repository.RacaRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class RacaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RacaRepository racaRepository;

	@Transactional
	public Raca salvar(Raca raca) {
		Raca racaExistente = racaRepository.porNome(raca.getNome());

		if (racaExistente != null && !racaExistente.equals(raca)) {
			throw new NegocioException("Já existe uma Raça com esse nome informado.");
		}
		return racaRepository.save(raca);
	}

	@Transactional
	public void remover(Raca raca) {
		racaRepository.remove(raca);
	}

	public Raca buscarPorId(Long id) {
		return racaRepository.porId(id);
	}

	public List<Raca> listarTodos() {
		return racaRepository.listarTodos();
	}

	public List<Raca> grupoCondicao(Raca op) {
		return racaRepository.grupoCondicao(op);
		
	}

	public Raca porId(Long id) {
		return racaRepository.porId(id);
	}

}
