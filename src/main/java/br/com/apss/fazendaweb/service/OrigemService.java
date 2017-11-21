package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Origem;
import br.com.apss.fazendaweb.repository.OrigemRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class OrigemService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrigemRepository origemRepository;

	@Transactional
	public Origem salvar(Origem origem) {
		Origem origemExistente = origemRepository.porNome(origem.getNome());

		if (origemExistente != null && !origemExistente.equals(origem)) {
			throw new NegocioException("Já existe uma Origem com esse nome informado.");
		}
		return origemRepository.save(origem);
	}

	@Transactional
	public void remover(Origem origem) {
		origemRepository.remove(origem);
	}

	public Origem buscarPorId(Long id) {
		return origemRepository.porId(id);
	}

	public List<Origem> listarTodos() {
		return origemRepository.listarTodos();
	}

	public List<Origem> grupoCondicao(Origem op) {
		return origemRepository.grupoCondicao(op);
		
	}

	public Origem porId(Long id) {
		return origemRepository.porId(id);
	}

}
