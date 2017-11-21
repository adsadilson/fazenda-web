package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.CausaMorte;
import br.com.apss.fazendaweb.repository.CausaMorteRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class CausaMorteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CausaMorteRepository causaMorteRepository;

	@Transactional
	public CausaMorte salvar(CausaMorte causaMorte) {
		CausaMorte causaMorteExistente = causaMorteRepository.porNome(causaMorte.getNome());

		if (causaMorteExistente != null && !causaMorteExistente.equals(causaMorte)) {
			throw new NegocioException("Já existe uma Causa da Morte com esse nome informado.");
		}
		return causaMorteRepository.save(causaMorte);
	}

	@Transactional
	public void remover(CausaMorte causaMorte) {
		causaMorteRepository.remove(causaMorte);
	}

	public CausaMorte buscarPorId(Long id) {
		return causaMorteRepository.porId(id);
	}

	public List<CausaMorte> listarTodos() {
		return causaMorteRepository.listarTodos();
	}

	public List<CausaMorte> grupoCondicao(CausaMorte op) {
		return causaMorteRepository.grupoCondicao(op);
		
	}

	public CausaMorte porId(Long id) {
		return causaMorteRepository.porId(id);
	}

}
