package br.com.apss.fazendaweb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.apss.fazendaweb.enums.AtivoInativo;
import br.com.apss.fazendaweb.enums.TipoProduto;
import br.com.apss.fazendaweb.model.GrupoProduto;
import br.com.apss.fazendaweb.model.Pessoa;
import br.com.apss.fazendaweb.model.Produto;
import br.com.apss.fazendaweb.model.UnidadeMedida;
import br.com.apss.fazendaweb.service.GrupoProdutoService;
import br.com.apss.fazendaweb.service.PessoaService;
import br.com.apss.fazendaweb.service.ProdutoService;
import br.com.apss.fazendaweb.service.UnidadeMedidaService;
import br.com.apss.fazendaweb.util.FacesUtil;

/*Adilson Paraguai*/
@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private List<Produto> produtos = new ArrayList<>();
	private Produto produtoSelecionado;

	@Inject
	ProdutoService produtoService;

	@Inject
	PessoaService fornecedorService;

	@Inject
	UnidadeMedidaService unidadeService;

	@Inject
	GrupoProdutoService categoriaService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}

	}

	private void carregarTabela() {
		produto = new Produto();
		produtos = produtoService.listarTodos();
	}

	public ProdutoBean() {
	}

	public List<AtivoInativo> getAtivoInativos() {
		return Arrays.asList(AtivoInativo.values());
	}

	public List<TipoProduto> getTipoProdutos() {
		return Arrays.asList(TipoProduto.values());
	}

	public void novoCadastro() {
		produto = new Produto();
		produto.setStatus(true);
	}

	public void salvar() {
		produtoService.salvar(this.produto);
		this.produtoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		produtoService.remover(this.produto);
		this.produtoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.produto = produtoService.buscarPorId(produtoSelecionado.getId());
	}

	public List<Pessoa> getFornecedores() {
		Pessoa p = new Pessoa();
		p.setFornecedor(true);
		p.setCliente(false);
		p.setFuncionario(false);
		p.setEmpresa(false);
		p.setProfissional(false);
		return fornecedorService.listarPorCondicao(p);
	}

	public List<UnidadeMedida> getUnidades() {
		UnidadeMedida u = new UnidadeMedida();
		u.setStatus(true);
		return unidadeService.grupoCondicao(u);
	}

	public List<GrupoProduto> getCategorias() {
		GrupoProduto gp = new GrupoProduto();
		gp.setStatus(true);
		return categoriaService.grupoCondicao(gp);
	}

	public void calcMargem() {
		BigDecimal n = produto.getVlrCusto();
		BigDecimal n3 = BigDecimal.ZERO;
		BigDecimal n2 = produto.getMargLucro();
		if (produto.getVlrCusto().signum() > 0 && produto.getMargLucro().signum() > 0) {
			n3 = (n.multiply(n2)).divide(new BigDecimal(100)).add(n);
			produto.setVlrVenda(n3);
		} else {
			produto.setVlrVenda(BigDecimal.ZERO);
		}
	}

	public void calcVenda() {
		BigDecimal n = produto.getVlrCusto();
		BigDecimal n3 = BigDecimal.ZERO;
		BigDecimal n2 = produto.getVlrVenda();
		if (n.signum() > 0 && n2.signum() > 0) {
			n3 = (n2.subtract(n)).divide(n, MathContext.DECIMAL128).multiply(new BigDecimal(100));
			produto.setMargLucro(n3);
		} else {
			produto.setMargLucro(BigDecimal.ZERO);
		}
	}

	/****************************** Getters e Setters *************************/

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
}

/****************************** Getters e Setters *************************/
