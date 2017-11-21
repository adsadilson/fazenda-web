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
import br.com.apss.fazendaweb.model.TipoSecagem;
import br.com.apss.fazendaweb.service.TipoSecagemService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class TipoSecagemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoSecagem tipoSecagem;
	private List<TipoSecagem> tipoSecagems = new ArrayList<>();
	private TipoSecagem tipoSecagemSelecionado;

	@Inject
	TipoSecagemService tipoSecagemService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Tipo Secagem: Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	
	private void carregarTabela() {
		tipoSecagems = tipoSecagemService.listarTodos();
	}

	public TipoSecagemBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.tipoSecagem = new TipoSecagem();
		this.tipoSecagem.setStatus(true);
	}

	public void salvar() {
		tipoSecagemService.salvar(this.tipoSecagem);
		this.tipoSecagemSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		tipoSecagemService.remover(this.tipoSecagem);
		this.tipoSecagemSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.tipoSecagem = tipoSecagemService.buscarPorId(tipoSecagemSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public TipoSecagem getTipoSecagem() {
		return tipoSecagem;
	}

	public void setTipoSecagem(TipoSecagem tipoSecagem) {
		this.tipoSecagem = tipoSecagem;
	}

	public List<TipoSecagem> getTipoSecagems() {
		return tipoSecagems;
	}

	public void setTipoSecagems(List<TipoSecagem> tipoSecagems) {
		this.tipoSecagems = tipoSecagems;
	}

	public TipoSecagem getTipoSecagemSelecionado() {
		return tipoSecagemSelecionado;
	}

	public void setTipoSecagemSelecionado(TipoSecagem tipoSecagemSelecionado) {
		this.tipoSecagemSelecionado = tipoSecagemSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
