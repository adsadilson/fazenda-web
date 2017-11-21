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
import br.com.apss.fazendaweb.model.Tanque;
import br.com.apss.fazendaweb.service.TanqueService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class TanqueBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Tanque tanque;
	private List<Tanque> tanques = new ArrayList<>();
	private Tanque tanqueSelecionado;

	@Inject
	TanqueService tanqueService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		tanques = tanqueService.listarTodos();
	}

	public TanqueBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.tanque = new Tanque();
		this.tanque.setStatus(true);
	}

	public void salvar() {
		tanqueService.salvar(this.tanque);
		this.tanqueSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		tanqueService.remover(this.tanque);
		this.tanqueSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.tanque = tanqueService.buscarPorId(tanqueSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Tanque getTanque() {
		return tanque;
	}

	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}

	public List<Tanque> getTanques() {
		return tanques;
	}

	public void setTanques(List<Tanque> tanques) {
		this.tanques = tanques;
	}

	public Tanque getTanqueSelecionado() {
		return tanqueSelecionado;
	}

	public void setTanqueSelecionado(Tanque tanqueSelecionado) {
		this.tanqueSelecionado = tanqueSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
