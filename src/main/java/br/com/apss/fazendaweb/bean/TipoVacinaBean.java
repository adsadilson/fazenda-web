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
import br.com.apss.fazendaweb.model.TipoVacina;
import br.com.apss.fazendaweb.service.TipoVacinaService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class TipoVacinaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoVacina tipoVacina;
	private List<TipoVacina> tipoVacinas = new ArrayList<>();
	private TipoVacina tipoVacinaSelecionado;

	@Inject
	TipoVacinaService tipoVacinaService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		tipoVacinas = tipoVacinaService.listarTodos();
	}

	public TipoVacinaBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.tipoVacina = new TipoVacina();
		this.tipoVacina.setStatus(true);
	}

	public void salvar() {
		tipoVacinaService.salvar(this.tipoVacina);
		this.tipoVacinaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		tipoVacinaService.remover(this.tipoVacina);
		this.tipoVacinaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.tipoVacina = tipoVacinaService.buscarPorId(tipoVacinaSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public TipoVacina getTipoVacina() {
		return tipoVacina;
	}

	public void setTipoVacina(TipoVacina tipoVacina) {
		this.tipoVacina = tipoVacina;
	}

	public List<TipoVacina> getTipoVacinas() {
		return tipoVacinas;
	}

	public void setTipoVacinas(List<TipoVacina> tipoVacinas) {
		this.tipoVacinas = tipoVacinas;
	}

	public TipoVacina getTipoVacinaSelecionado() {
		return tipoVacinaSelecionado;
	}

	public void setTipoVacinaSelecionado(TipoVacina tipoVacinaSelecionado) {
		this.tipoVacinaSelecionado = tipoVacinaSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
