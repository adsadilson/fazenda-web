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
import br.com.apss.fazendaweb.model.Origem;
import br.com.apss.fazendaweb.service.OrigemService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class OrigemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Origem origem;
	private List<Origem> origems = new ArrayList<>();
	private Origem origemSelecionado;

	@Inject
	OrigemService origemService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		origems = origemService.listarTodos();
	}

	public OrigemBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.origem = new Origem();
		this.origem.setStatus(true);
	}

	public void salvar() {
		origemService.salvar(this.origem);
		this.origemSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		origemService.remover(this.origem);
		this.origemSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.origem = origemService.buscarPorId(origemSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public List<Origem> getOrigems() {
		return origems;
	}

	public void setOrigems(List<Origem> origems) {
		this.origems = origems;
	}

	public Origem getOrigemSelecionado() {
		return origemSelecionado;
	}

	public void setOrigemSelecionado(Origem origemSelecionado) {
		this.origemSelecionado = origemSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
