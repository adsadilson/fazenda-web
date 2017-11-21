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
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa funcionario;
	private List<Pessoa> funcionarios = new ArrayList<>();
	private Pessoa funcionarioSelecionado;

	@Inject
	PessoaService funcionarioService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		funcionario = new Pessoa();
		funcionario.setFuncionario(true);
		funcionario.setCliente(false);
		funcionario.setEmpresa(false);
		funcionario.setFornecedor(false);
		funcionario.setProfissional(false);
		funcionarios = funcionarioService.listarPorCondicao(funcionario);
	}

	public FuncionarioBean() {
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
		funcionario = new Pessoa();
		funcionario.setTrabalha(false);
		funcionario.setContaConjunta(false);
		funcionario.setStatus(true);
		funcionario.setTipoPessoa("F");
	}

	public void salvar() {
		funcionario.setFuncionario(true);
		funcionarioService.salvar(this.funcionario);
		this.funcionarioSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		funcionarioService.remover(this.funcionario);
		this.funcionarioSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.funcionario = funcionarioService.buscarPorId(funcionarioSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Pessoa getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Pessoa funcionario) {
		this.funcionario = funcionario;
	}

	public List<Pessoa> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Pessoa> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Pessoa getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Pessoa funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}
}

	/****************************** Getters e Setters *************************/

