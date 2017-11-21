package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Pelagem;
import br.com.apss.fazendaweb.repository.PelagemRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class PelagemService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PelagemRepository pelagemRepository;

	@Transactional
	public Pelagem salvar(Pelagem pelagem) {
		Pelagem pelagemExistente = pelagemRepository.porNome(pelagem.getNome());

		if (pelagemExistente != null && !pelagemExistente.equals(pelagem)) {
			throw new NegocioException("Já existe uma Pelagem com esse nome informado.");
		}
		return pelagemRepository.save(pelagem);
	}

	@Transactional
	public void remover(Pelagem pelagem) {
		pelagemRepository.remove(pelagem);
	}

	public Pelagem buscarPorId(Long id) {
		return pelagemRepository.porId(id);
	}

	public List<Pelagem> listarTodos() {
		return pelagemRepository.listarTodos();
	}

	public List<Pelagem> grupoCondicao(Pelagem op) {
		return pelagemRepository.grupoCondicao(op);
		
	}

	public Pelagem porId(Long id) {
		return pelagemRepository.porId(id);
	}

}
