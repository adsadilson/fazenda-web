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
import br.com.apss.fazendaweb.model.PlanoConta;
import br.com.apss.fazendaweb.model.filter.PlanoContaFilter;
import br.com.apss.fazendaweb.service.PlanoContaService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class PlanoContaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PlanoConta planoConta;
	private List<PlanoConta> planoContas = new ArrayList<>();
	private PlanoConta planoContaSelecionado;
	private PlanoContaFilter filtro;

	@Inject
	PlanoContaService planoContaService;

	/****************************** Metodos *************************/

	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		planoContas = planoContaService.listarTodos();
	}
	
	public void pesquisar() {
		planoContas = planoContaService.filtrados(filtro);
	}
	
	public void novoFiltro() {
		filtro = new PlanoContaFilter();
	}

	public PlanoContaBean() {
		filtro= new PlanoContaFilter();
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}
	
	public void clonarCadastro() {
		planoConta = planoContaService.buscarPorId(planoContaSelecionado.getId());
		planoConta.setId(null);
	}

	public void novoCadastro() {
		this.planoConta = new PlanoConta();
		this.planoConta.setStatus(true);
	}

	public void salvar() {
		planoContaService.salvar(this.planoConta);
		this.planoContaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		planoContaService.remover(this.planoConta);
		this.planoContaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.planoConta = planoContaService.buscarPorId(planoContaSelecionado.getId());
	}
	
	public List<PlanoConta> getPlanoContaPais(){
		return planoContaService.listarTodos();
	}

	/****************************** Getters e Setters *************************/

	
	
	public PlanoConta getPlanoConta() {
		return planoConta;
	}

	public PlanoContaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PlanoContaFilter filtro) {
		this.filtro = filtro;
	}

	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}

	public List<PlanoConta> getPlanoContas() {
		return planoContas;
	}

	public void setPlanoContas(List<PlanoConta> planoContas) {
		this.planoContas = planoContas;
	}

	public PlanoConta getPlanoContaSelecionado() {
		return planoContaSelecionado;
	}

	public void setPlanoContaSelecionado(PlanoConta planoContaSelecionado) {
		this.planoContaSelecionado = planoContaSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
