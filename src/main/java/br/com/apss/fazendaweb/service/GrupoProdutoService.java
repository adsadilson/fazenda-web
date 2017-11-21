package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.GrupoProduto;
import br.com.apss.fazendaweb.repository.GrupoProdutoRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class GrupoProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoProdutoRepository grupoProdutoRepository;

	@Transactional
	public GrupoProduto salvar(GrupoProduto grupoProduto) {
		GrupoProduto grupoProdutoExistente = grupoProdutoRepository.porNome(grupoProduto.getNome());

		if (grupoProdutoExistente != null && !grupoProdutoExistente.equals(grupoProduto)) {
			throw new NegocioException("Já existe uma Grupo de Produto com esse nome informado.");
		}
		return grupoProdutoRepository.save(grupoProduto);
	}

	@Transactional
	public void remover(GrupoProduto grupoProduto) {
		grupoProdutoRepository.remove(grupoProduto);
	}

	public GrupoProduto buscarPorId(Long id) {
		return grupoProdutoRepository.porId(id);
	}

	public List<GrupoProduto> listarTodos() {
		return grupoProdutoRepository.listarTodos();
	}

	public List<GrupoProduto> grupoCondicao(GrupoProduto op) {
		return grupoProdutoRepository.grupoCondicao(op);
		
	}

	public GrupoProduto porId(Long id) {
		return grupoProdutoRepository.porId(id);
	}

}
