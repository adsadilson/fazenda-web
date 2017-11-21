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
import br.com.apss.fazendaweb.model.TipoTratamento;
import br.com.apss.fazendaweb.service.TipoTratamentoService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class TipoTratamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoTratamento tipoTratamento;
	private List<TipoTratamento> tipoTratamentos = new ArrayList<>();
	private TipoTratamento tipoTratamentoSelecionado;

	@Inject
	TipoTratamentoService tipoTratamentoService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		tipoTratamentos = tipoTratamentoService.listarTodos();
	}

	public TipoTratamentoBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.tipoTratamento = new TipoTratamento();
		this.tipoTratamento.setStatus(true);
	}

	public void salvar() {
		tipoTratamentoService.salvar(this.tipoTratamento);
		this.tipoTratamentoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		tipoTratamentoService.remover(this.tipoTratamento);
		this.tipoTratamentoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.tipoTratamento = tipoTratamentoService.buscarPorId(tipoTratamentoSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public TipoTratamento getTipoTratamento() {
		return tipoTratamento;
	}

	public void setTipoTratamento(TipoTratamento tipoTratamento) {
		this.tipoTratamento = tipoTratamento;
	}

	public List<TipoTratamento> getTipoTratamentos() {
		return tipoTratamentos;
	}

	public void setTipoTratamentos(List<TipoTratamento> tipoTratamentos) {
		this.tipoTratamentos = tipoTratamentos;
	}

	public TipoTratamento getTipoTratamentoSelecionado() {
		return tipoTratamentoSelecionado;
	}

	public void setTipoTratamentoSelecionado(TipoTratamento tipoTratamentoSelecionado) {
		this.tipoTratamentoSelecionado = tipoTratamentoSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
