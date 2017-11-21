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
import br.com.apss.fazendaweb.model.CausaMorte;
import br.com.apss.fazendaweb.service.CausaMorteService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class CausaMorteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private CausaMorte causaMorte;
	private List<CausaMorte> causaMortes = new ArrayList<>();
	private CausaMorte causaMorteSelecionado;

	@Inject
	CausaMorteService causaMorteService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		causaMortes = causaMorteService.listarTodos();
	}

	public CausaMorteBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.causaMorte = new CausaMorte();
		this.causaMorte.setStatus(true);
	}

	public void salvar() {
		causaMorteService.salvar(this.causaMorte);
		this.causaMorteSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		causaMorteService.remover(this.causaMorte);
		this.causaMorteSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.causaMorte = causaMorteService.buscarPorId(causaMorteSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public CausaMorte getCausaMorte() {
		return causaMorte;
	}

	public void setCausaMorte(CausaMorte causaMorte) {
		this.causaMorte = causaMorte;
	}

	public List<CausaMorte> getCausaMortes() {
		return causaMortes;
	}

	public void setCausaMortes(List<CausaMorte> causaMortes) {
		this.causaMortes = causaMortes;
	}

	public CausaMorte getCausaMorteSelecionado() {
		return causaMorteSelecionado;
	}

	public void setCausaMorteSelecionado(CausaMorte causaMorteSelecionado) {
		this.causaMorteSelecionado = causaMorteSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
