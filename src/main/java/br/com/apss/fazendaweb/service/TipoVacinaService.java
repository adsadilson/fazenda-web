package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.TipoVacina;
import br.com.apss.fazendaweb.repository.TipoVacinaRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class TipoVacinaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoVacinaRepository tipoVacinaRepository;

	@Transactional
	public TipoVacina salvar(TipoVacina tipoVacina) {
		TipoVacina tipoVacinaExistente = tipoVacinaRepository.porNome(tipoVacina.getNome());

		if (tipoVacinaExistente != null && !tipoVacinaExistente.equals(tipoVacina)) {
			throw new NegocioException("Já existe um Tipo Vacina com esse nome informado.");
		}
		return tipoVacinaRepository.save(tipoVacina);
	}

	@Transactional
	public void remover(TipoVacina tipoVacina) {
		tipoVacinaRepository.remove(tipoVacina);
	}

	public TipoVacina buscarPorId(Long id) {
		return tipoVacinaRepository.porId(id);
	}

	public List<TipoVacina> listarTodos() {
		return tipoVacinaRepository.listarTodos();
	}

	public List<TipoVacina> grupoCondicao(TipoVacina op) {
		return tipoVacinaRepository.grupoCondicao(op);
		
	}

	public TipoVacina porId(Long id) {
		return tipoVacinaRepository.porId(id);
	}

}
