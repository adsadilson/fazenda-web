package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.TipoSecagem;
import br.com.apss.fazendaweb.repository.TipoSecagemRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class TipoSecagemService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoSecagemRepository tipoSecagemRepository;

	@Transactional
	public TipoSecagem salvar(TipoSecagem tipoSecagem) {
		TipoSecagem tipoSecagemExistente = tipoSecagemRepository.porNome(tipoSecagem.getNome());

		if (tipoSecagemExistente != null && !tipoSecagemExistente.equals(tipoSecagem)) {
			throw new NegocioException("Já existe um Tipo Secagem com esse nome informado.");
		}
		return tipoSecagemRepository.save(tipoSecagem);
	}

	@Transactional
	public void remover(TipoSecagem tipoSecagem) {
		tipoSecagemRepository.remove(tipoSecagem);
	}

	public TipoSecagem buscarPorId(Long id) {
		return tipoSecagemRepository.porId(id);
	}

	public List<TipoSecagem> listarTodos() {
		return tipoSecagemRepository.listarTodos();
	}

	public List<TipoSecagem> grupoCondicao(TipoSecagem op) {
		return tipoSecagemRepository.grupoCondicao(op);
		
	}

	public TipoSecagem porId(Long id) {
		return tipoSecagemRepository.porId(id);
	}

}
