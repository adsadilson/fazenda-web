package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Exame;
import br.com.apss.fazendaweb.repository.ExameRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class ExameService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ExameRepository ExameRepository;

	@Transactional
	public Exame salvar(Exame Exame) {
		/*Exame ExameExistente = ExameRepository.porNome(Exame.getNome());

		if (ExameExistente != null && !ExameExistente.equals(Exame)) {
			throw new NegocioException("Já existe uma Exame com esse nome informado.");
		}*/
		return ExameRepository.save(Exame);
	}

	@Transactional
	public void remover(Exame Exame) {
		ExameRepository.remove(Exame);
	}

	public Exame buscarPorId(Long id) {
		return ExameRepository.porId(id);
	}

	public List<Exame> listarTodos() {
		return ExameRepository.listarTodos();
	}

	public Exame porId(Long id) {
		return ExameRepository.porId(id);
	}

}
