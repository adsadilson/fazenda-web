package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Produto;
import br.com.apss.fazendaweb.repository.ProdutoRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository produtoRepository;

	@Transactional
	public Produto salvar(Produto produto) {
		Produto produtoExistente = produtoRepository.porCodigoBarra(produto.getCodigoBarra());

		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			FacesContext.getCurrentInstance().validationFailed();
			throw new NegocioException("Já existe uma Produto com esse codigo de barra informado.");
		}
		if (produto.getVlrCusto().compareTo(produto.getVlrVenda()) == 1){
			FacesContext.getCurrentInstance().validationFailed();
			throw new NegocioException("O valor de Custo não pode ser maior que o valor de venda.");
		}
		
		return produtoRepository.save(produto);
	}

	@Transactional
	public void remover(Produto produto) {
		produtoRepository.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return produtoRepository.porId(id);
	}

	public List<Produto> listarTodos() {
		return produtoRepository.listarTodos();
	}
	
	public List<Produto> grupoCondicao(Produto op) {
		return produtoRepository.grupoCondicao(op);
		
	}

	public Produto porId(Long id) {
		return produtoRepository.porId(id);
	}

}
