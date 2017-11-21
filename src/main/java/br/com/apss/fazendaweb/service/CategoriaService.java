package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Categoria;
import br.com.apss.fazendaweb.repository.CategoriaRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class CategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaRepository categoriaRepository;

	@Transactional
	public Categoria salvar(Categoria categoria) {
		Categoria categoriaExistente = categoriaRepository.porNome(categoria.getNome());

		if (categoriaExistente != null && !categoriaExistente.equals(categoria)) {
			throw new NegocioException("Já existe uma Categoria com esse nome informado.");
		}
		return categoriaRepository.save(categoria);
	}

	@Transactional
	public void remover(Categoria categoria) {
		categoriaRepository.remove(categoria);
	}

	public Categoria buscarPorId(Long id) {
		return categoriaRepository.porId(id);
	}

	public List<Categoria> listarTodos() {
		return categoriaRepository.listarTodos();
	}

	public List<Categoria> grupoCondicao(Categoria op) {
		return categoriaRepository.grupoCondicao(op);
		
	}

	public Categoria porId(Long id) {
		return categoriaRepository.porId(id);
	}

}
