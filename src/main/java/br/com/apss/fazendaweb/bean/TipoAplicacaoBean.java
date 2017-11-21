package br.com.apss.fazendaweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.apss.fazendaweb.enums.AtivoInativo;
import br.com.apss.fazendaweb.model.TipoAplicacao;
import br.com.apss.fazendaweb.service.TipoAplicacaoService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class TipoAplicacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoAplicacao tipoAplicacao;
	private List<TipoAplicacao> tipoAplicacaos = new ArrayList<>();
	private TipoAplicacao tipoAplicacaoSelecionado;

	@Inject
	TipoAplicacaoService tipoAplicacaoService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		tipoAplicacaos = tipoAplicacaoService.listarTodos();
	}

	public TipoAplicacaoBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.tipoAplicacao = new TipoAplicacao();
		this.tipoAplicacao.setStatus(true);
	}

	public void salvar() {
		tipoAplicacaoService.salvar(this.tipoAplicacao);
		this.tipoAplicacaoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		tipoAplicacaoService.remover(this.tipoAplicacao);
		this.tipoAplicacaoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.tipoAplicacao = tipoAplicacaoService.buscarPorId(tipoAplicacaoSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public TipoAplicacao getTipoAplicacao() {
		return tipoAplicacao;
	}

	public void setTipoAplicacao(TipoAplicacao tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
	}

	public List<TipoAplicacao> getTipoAplicacaos() {
		return tipoAplicacaos;
	}

	public void setTipoAplicacaos(List<TipoAplicacao> tipoAplicacaos) {
		this.tipoAplicacaos = tipoAplicacaos;
	}

	public TipoAplicacao getTipoAplicacaoSelecionado() {
		return tipoAplicacaoSelecionado;
	}

	public void setTipoAplicacaoSelecionado(TipoAplicacao tipoAplicacaoSelecionado) {
		this.tipoAplicacaoSelecionado = tipoAplicacaoSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
