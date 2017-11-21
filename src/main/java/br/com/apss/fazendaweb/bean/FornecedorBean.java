package br.com.apss.fazendaweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.apss.fazendaweb.enums.AtivoInativo;
import br.com.apss.fazendaweb.enums.Estado;
import br.com.apss.fazendaweb.enums.EstadoCivil;
import br.com.apss.fazendaweb.enums.Sexo;
import br.com.apss.fazendaweb.enums.TipoDoc;
import br.com.apss.fazendaweb.enums.TipoPessoa;
import br.com.apss.fazendaweb.model.Pessoa;
import br.com.apss.fazendaweb.service.PessoaService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa fornecedor;
	private List<Pessoa> fornecedors = new ArrayList<>();
	private Pessoa fornecedorSelecionado;

	@Inject
	PessoaService fornecedorService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		fornecedor = new Pessoa();
		fornecedor.setFornecedor(true);
		fornecedor.setEmpresa(false);
		fornecedor.setCliente(false);
		fornecedor.setFuncionario(false);
		fornecedor.setProfissional(false);
		fornecedors = fornecedorService.listarPorCondicao(fornecedor);
	}

	public FornecedorBean() {
	}

	public List<AtivoInativo> getAtivoInativos() {
		return Arrays.asList(AtivoInativo.values());
	}

	public List<Sexo> getSexos() {
		return Arrays.asList(Sexo.values());
	}

	public List<Estado> getEstados() {
		return Arrays.asList(Estado.values());
	}

	public List<EstadoCivil> getEstadoCivis() {
		return Arrays.asList(EstadoCivil.values());
	}

	public List<TipoDoc> getTipoDocs() {
		return Arrays.asList(TipoDoc.values());
	}

	public List<TipoPessoa> getTipoPessoas() {
		return Arrays.asList(TipoPessoa.values());
	}

	public void novoCadastro() {
		fornecedor = new Pessoa();
		fornecedor.setTrabalha(false);
		fornecedor.setContaConjunta(false);
		fornecedor.setStatus(true);
		fornecedor.setTipoPessoa("J");
	}

	public void salvar() {
		fornecedor.setFornecedor(true);
		fornecedorService.salvar(this.fornecedor);
		this.fornecedorSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		fornecedorService.remover(this.fornecedor);
		this.fornecedorSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.fornecedor = fornecedorService.buscarPorId(fornecedorSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Pessoa getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Pessoa fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Pessoa> getFornecedors() {
		return fornecedors;
	}

	public void setFornecedors(List<Pessoa> fornecedors) {
		this.fornecedors = fornecedors;
	}

	public Pessoa getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Pessoa fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}
}

/****************************** Getters e Setters *************************/
