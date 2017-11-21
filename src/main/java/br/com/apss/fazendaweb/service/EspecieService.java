package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Especie;
import br.com.apss.fazendaweb.repository.EspecieRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class EspecieService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EspecieRepository especieRepository;

	@Transactional
	public Especie salvar(Especie especie) {
		Especie especieExistente = especieRepository.porNome(especie.getNome());

		if (especieExistente != null && !especieExistente.equals(especie)) {
			throw new NegocioException("Já existe uma Especie com esse nome informado.");
		}
		return especieRepository.save(especie);
	}

	@Transactional
	public void remover(Especie especie) {
		especieRepository.remove(especie);
	}

	public Especie buscarPorId(Long id) {
		return especieRepository.porId(id);
	}

	public List<Especie> listarTodos() {
		return especieRepository.listarTodos();
	}

	public List<Especie> grupoCondicao(Especie op) {
		return especieRepository.grupoCondicao(op);
		
	}

	public Especie porId(Long id) {
		return especieRepository.porId(id);
	}

}
