package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.TipoTratamento;
import br.com.apss.fazendaweb.repository.TipoTratamentoRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class TipoTratamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoTratamentoRepository tipoTratamentoRepository;

	@Transactional
	public TipoTratamento salvar(TipoTratamento tipoTratamento) {
		TipoTratamento tipoTratamentoExistente = tipoTratamentoRepository.porNome(tipoTratamento.getNome());

		if (tipoTratamentoExistente != null && !tipoTratamentoExistente.equals(tipoTratamento)) {
			throw new NegocioException("Já existe um Tipo Tratamento com esse nome informado.");
		}
		return tipoTratamentoRepository.save(tipoTratamento);
	}

	@Transactional
	public void remover(TipoTratamento tipoTratamento) {
		tipoTratamentoRepository.remove(tipoTratamento);
	}

	public TipoTratamento buscarPorId(Long id) {
		return tipoTratamentoRepository.porId(id);
	}

	public List<TipoTratamento> listarTodos() {
		return tipoTratamentoRepository.listarTodos();
	}

	public List<TipoTratamento> grupoCondicao(TipoTratamento op) {
		return tipoTratamentoRepository.grupoCondicao(op);
		
	}

	public TipoTratamento porId(Long id) {
		return tipoTratamentoRepository.porId(id);
	}

}
