package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.TipoParto;
import br.com.apss.fazendaweb.repository.TipoPartoRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class TipoPartoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoPartoRepository tipoPartoRepository;

	@Transactional
	public TipoParto salvar(TipoParto tipoParto) {
		TipoParto tipoPartoExistente = tipoPartoRepository.porNome(tipoParto.getNome());

		if (tipoPartoExistente != null && !tipoPartoExistente.equals(tipoParto)) {
			throw new NegocioException("Já existe um Tipo Parto com esse nome informado.");
		}
		return tipoPartoRepository.save(tipoParto);
	}

	@Transactional
	public void remover(TipoParto tipoParto) {
		tipoPartoRepository.remove(tipoParto);
	}

	public TipoParto buscarPorId(Long id) {
		return tipoPartoRepository.porId(id);
	}

	public List<TipoParto> listarTodos() {
		return tipoPartoRepository.listarTodos();
	}

	public List<TipoParto> grupoCondicao(TipoParto op) {
		return tipoPartoRepository.grupoCondicao(op);
		
	}

	public TipoParto porId(Long id) {
		return tipoPartoRepository.porId(id);
	}

}
