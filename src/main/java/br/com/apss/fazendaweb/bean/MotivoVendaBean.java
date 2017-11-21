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
import br.com.apss.fazendaweb.model.MotivoVenda;
import br.com.apss.fazendaweb.service.MotivoVendaService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class MotivoVendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private MotivoVenda motivoVenda;
	private List<MotivoVenda> motivoVendas = new ArrayList<>();
	private MotivoVenda motivoVendaSelecionado;

	@Inject
	MotivoVendaService motivoVendaService;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		motivoVendas = motivoVendaService.listarTodos();
	}

	public MotivoVendaBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.motivoVenda = new MotivoVenda();
		this.motivoVenda.setStatus(true);
	}

	public void salvar() {
		motivoVendaService.salvar(this.motivoVenda);
		this.motivoVendaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		motivoVendaService.remover(this.motivoVenda);
		this.motivoVendaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.motivoVenda = motivoVendaService.buscarPorId(motivoVendaSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public MotivoVenda getMotivoVenda() {
		return motivoVenda;
	}

	public void setMotivoVenda(MotivoVenda motivoVenda) {
		this.motivoVenda = motivoVenda;
	}

	public List<MotivoVenda> getMotivoVendas() {
		return motivoVendas;
	}

	public void setMotivoVendas(List<MotivoVenda> motivoVendas) {
		this.motivoVendas = motivoVendas;
	}

	public MotivoVenda getMotivoVendaSelecionado() {
		return motivoVendaSelecionado;
	}

	public void setMotivoVendaSelecionado(MotivoVenda motivoVendaSelecionado) {
		this.motivoVendaSelecionado = motivoVendaSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
