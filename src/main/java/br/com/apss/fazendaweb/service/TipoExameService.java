package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.TipoExame;
import br.com.apss.fazendaweb.repository.TipoExameRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class TipoExameService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoExameRepository tipoExameRepository;

	@Transactional
	public TipoExame salvar(TipoExame tipoExame) {
		TipoExame tipoExameExistente = tipoExameRepository.porNome(tipoExame.getNome());

		if (tipoExameExistente != null && !tipoExameExistente.equals(tipoExame)) {
			throw new NegocioException("Já existe um Tipo Exame com esse nome informado.");
		}
		return tipoExameRepository.save(tipoExame);
	}

	@Transactional
	public void remover(TipoExame tipoExame) {
		tipoExameRepository.remove(tipoExame);
	}

	public TipoExame buscarPorId(Long id) {
		return tipoExameRepository.porId(id);
	}

	public List<TipoExame> listarTodos() {
		return tipoExameRepository.listarTodos();
	}

	public List<TipoExame> grupoCondicao(TipoExame op) {
		return tipoExameRepository.grupoCondicao(op);
		
	}

	public TipoExame porId(Long id) {
		return tipoExameRepository.porId(id);
	}

}
