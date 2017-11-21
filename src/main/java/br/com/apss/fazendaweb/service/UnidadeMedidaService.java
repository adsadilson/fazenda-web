package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.UnidadeMedida;
import br.com.apss.fazendaweb.repository.UnidadeMedidaRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class UnidadeMedidaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UnidadeMedidaRepository unidadeMedidaRepository;

	@Transactional
	public UnidadeMedida salvar(UnidadeMedida unidadeMedida) {
		UnidadeMedida unidadeMedidaExistente = unidadeMedidaRepository.porNome(unidadeMedida.getNome());

		if (unidadeMedidaExistente != null && !unidadeMedidaExistente.equals(unidadeMedida)) {
			throw new NegocioException("Já existe uma Unidade de Medida com esse nome informado.");
		}
		return unidadeMedidaRepository.save(unidadeMedida);
	}

	@Transactional
	public void remover(UnidadeMedida unidadeMedida) {
		unidadeMedidaRepository.remove(unidadeMedida);
	}

	public UnidadeMedida buscarPorId(Long id) {
		return unidadeMedidaRepository.porId(id);
	}

	public List<UnidadeMedida> listarTodos() {
		return unidadeMedidaRepository.listarTodos();
	}

	public List<UnidadeMedida> grupoCondicao(UnidadeMedida op) {
		return unidadeMedidaRepository.grupoCondicao(op);
		
	}

	public UnidadeMedida porId(Long id) {
		return unidadeMedidaRepository.porId(id);
	}

}
