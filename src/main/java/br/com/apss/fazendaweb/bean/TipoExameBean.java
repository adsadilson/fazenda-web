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
import br.com.apss.fazendaweb.model.TipoExame;
import br.com.apss.fazendaweb.service.TipoExameService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class TipoExameBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoExame tipoExame;
	private List<TipoExame> tipoExames = new ArrayList<>();
	private TipoExame tipoExameSelecionado;

	@Inject
	TipoExameService tipoExameService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Tipo Parto: Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		tipoExames = tipoExameService.listarTodos();
	}

	public TipoExameBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.tipoExame = new TipoExame();
		this.tipoExame.setStatus(true);
	}

	public void salvar() {
		tipoExameService.salvar(this.tipoExame);
		this.tipoExameSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		tipoExameService.remover(this.tipoExame);
		this.tipoExameSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.tipoExame = tipoExameService.buscarPorId(tipoExameSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public List<TipoExame> getTipoExames() {
		return tipoExames;
	}

	public void setTipoExames(List<TipoExame> tipoExames) {
		this.tipoExames = tipoExames;
	}

	public TipoExame getTipoExameSelecionado() {
		return tipoExameSelecionado;
	}

	public void setTipoExameSelecionado(TipoExame tipoExameSelecionado) {
		this.tipoExameSelecionado = tipoExameSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
