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
public class ProfissionalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa profissional;
	private List<Pessoa> profissionals = new ArrayList<>();
	private Pessoa profissionalSelecionado;

	@Inject
	PessoaService profissionalService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
		
	}
	

	private void carregarTabela() {
		profissional = new Pessoa();
		profissional.setProfissional(true);
		profissional.setEmpresa(false);
		profissional.setFuncionario(false);
		profissional.setCliente(false);
		profissional.setFornecedor(false);
		profissionals = profissionalService.listarPorCondicao(profissional);
	}

	public ProfissionalBean() {
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
		profissional = new Pessoa();
		profissional.setTrabalha(false);
		profissional.setContaConjunta(false);
		profissional.setStatus(true);
		profissional.setTipoPessoa("J");
	}

	public void salvar() {
		profissional.setProfissional(true);
		profissionalService.salvar(this.profissional);
		this.profissionalSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		profissionalService.remover(this.profissional);
		this.profissionalSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.profissional = profissionalService.buscarPorId(profissionalSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Pessoa getProfissional() {
		return profissional;
	}

	public void setProfissional(Pessoa profissional) {
		this.profissional = profissional;
	}

	public List<Pessoa> getProfissionals() {
		return profissionals;
	}

	public void setProfissionals(List<Pessoa> profissionals) {
		this.profissionals = profissionals;
	}

	public Pessoa getProfissionalSelecionado() {
		return profissionalSelecionado;
	}

	public void setProfissionalSelecionado(Pessoa profissionalSelecionado) {
		this.profissionalSelecionado = profissionalSelecionado;
	}
}

/****************************** Getters e Setters *************************/
