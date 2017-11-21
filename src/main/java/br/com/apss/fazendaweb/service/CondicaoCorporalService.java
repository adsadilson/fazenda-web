package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.CondicaoCorporal;
import br.com.apss.fazendaweb.repository.CondicaoCorporalRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class CondicaoCorporalService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CondicaoCorporalRepository condicaoCorporalRepository;

	@Transactional
	public CondicaoCorporal salvar(CondicaoCorporal condicaoCorporal) {
		CondicaoCorporal condicaoCorporalExistente = condicaoCorporalRepository.porNome(condicaoCorporal.getNome());

		if (condicaoCorporalExistente != null && !condicaoCorporalExistente.equals(condicaoCorporal)) {
			throw new NegocioException("Já existe uma CondicaoCorporal com esse nome informado.");
		}
		return condicaoCorporalRepository.save(condicaoCorporal);
	}

	@Transactional
	public void remover(CondicaoCorporal condicaoCorporal) {
		condicaoCorporalRepository.remove(condicaoCorporal);
	}

	public CondicaoCorporal buscarPorId(Long id) {
		return condicaoCorporalRepository.porId(id);
	}

	public List<CondicaoCorporal> listarTodos() {
		return condicaoCorporalRepository.listarTodos();
	}
	
	public List<CondicaoCorporal> listarTodos(Boolean status) {
		return condicaoCorporalRepository.listarTodos(status);
	}

	public List<CondicaoCorporal> grupoCondicao(CondicaoCorporal op) {
		return condicaoCorporalRepository.grupoCondicao(op);
		
	}

	public CondicaoCorporal porId(Long id) {
		return condicaoCorporalRepository.porId(id);
	}

}
