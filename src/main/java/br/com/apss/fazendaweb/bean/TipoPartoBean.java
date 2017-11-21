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
import br.com.apss.fazendaweb.model.TipoParto;
import br.com.apss.fazendaweb.service.TipoPartoService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class TipoPartoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoParto tipoParto;
	private List<TipoParto> tipoPartos = new ArrayList<>();
	private TipoParto tipoPartoSelecionado;

	@Inject
	TipoPartoService tipoPartoService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Tipo Parto: Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		tipoPartos = tipoPartoService.listarTodos();
	}

	public TipoPartoBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.tipoParto = new TipoParto();
		this.tipoParto.setStatus(true);
	}

	public void salvar() {
		tipoPartoService.salvar(this.tipoParto);
		this.tipoPartoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		tipoPartoService.remover(this.tipoParto);
		this.tipoPartoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.tipoParto = tipoPartoService.buscarPorId(tipoPartoSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public TipoParto getTipoParto() {
		return tipoParto;
	}

	public void setTipoParto(TipoParto tipoParto) {
		this.tipoParto = tipoParto;
	}

	public List<TipoParto> getTipoPartos() {
		return tipoPartos;
	}

	public void setTipoPartos(List<TipoParto> tipoPartos) {
		this.tipoPartos = tipoPartos;
	}

	public TipoParto getTipoPartoSelecionado() {
		return tipoPartoSelecionado;
	}

	public void setTipoPartoSelecionado(TipoParto tipoPartoSelecionado) {
		this.tipoPartoSelecionado = tipoPartoSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
