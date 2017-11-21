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
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa cliente;
	private List<Pessoa> clientes = new ArrayList<>();
	private Pessoa clienteSelecionado;

	@Inject
	PessoaService clienteService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		cliente = new Pessoa();
		cliente.setCliente(true);
		cliente.setFuncionario(false);
		cliente.setEmpresa(false);
		cliente.setFornecedor(false);
		cliente.setProfissional(false);
		clientes = clienteService.listarPorCondicao(cliente);
	}

	public ClienteBean() {
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
		cliente = new Pessoa();
		cliente.setTrabalha(false);
		cliente.setContaConjunta(false);
		cliente.setStatus(true);
		cliente.setTipoPessoa("J");
	}

	public void salvar() {
		cliente.setCliente(true);
		clienteService.salvar(this.cliente);
		this.clienteSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		clienteService.remover(this.cliente);
		this.clienteSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.cliente = clienteService.buscarPorId(clienteSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public List<Pessoa> getClientes() {
		return clientes;
	}

	public void setClientes(List<Pessoa> clientes) {
		this.clientes = clientes;
	}

	public Pessoa getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Pessoa clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
}

	/****************************** Getters e Setters *************************/

