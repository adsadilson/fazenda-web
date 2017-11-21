package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.TipoAplicacao;
import br.com.apss.fazendaweb.repository.TipoAplicacaoRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class TipoAplicacaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoAplicacaoRepository tipoAplicacaoRepository;

	@Transactional
	public TipoAplicacao salvar(TipoAplicacao TipoAplicacao) {
		TipoAplicacao TipoAplicacaoExistente = tipoAplicacaoRepository.porNome(TipoAplicacao.getNome());

		if (TipoAplicacaoExistente != null && !TipoAplicacaoExistente.equals(TipoAplicacao)) {
			throw new NegocioException("Já existe um Tipo Aplicação com esse nome informado.");
		}
		return tipoAplicacaoRepository.save(TipoAplicacao);
	}

	@Transactional
	public void remover(TipoAplicacao TipoAplicacao) {
		tipoAplicacaoRepository.remove(TipoAplicacao);
	}

	public TipoAplicacao buscarPorId(Long id) {
		return tipoAplicacaoRepository.porId(id);
	}

	public List<TipoAplicacao> listarTodos() {
		return tipoAplicacaoRepository.listarTodos();
	}

	public List<TipoAplicacao> grupoCondicao(TipoAplicacao op) {
		return tipoAplicacaoRepository.grupoCondicao(op);
		
	}

	public TipoAplicacao porId(Long id) {
		return tipoAplicacaoRepository.porId(id);
	}

}
