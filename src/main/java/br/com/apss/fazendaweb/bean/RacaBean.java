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
import br.com.apss.fazendaweb.model.Especie;
import br.com.apss.fazendaweb.model.Raca;
import br.com.apss.fazendaweb.repository.EspecieRepository;
import br.com.apss.fazendaweb.service.RacaService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class RacaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Raca raca;
	private List<Raca> racas = new ArrayList<>();
	private Raca racaSelecionado;

	@Inject
	RacaService racaService;
	
	@Inject
	EspecieRepository especieRepository;

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		racas = racaService.listarTodos();
	}

	public List<Especie> getEspecies() {
		return especieRepository.listarTodos();
		
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.raca = new Raca();
		this.raca.setStatus(true);
	}

	public void salvar() {
		racaService.salvar(this.raca);
		this.racaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
	}

	public void excluir() {
		racaService.remover(this.raca);
		this.racaSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.raca = racaService.buscarPorId(racaSelecionado.getId());
	}

	/****************************** Getters e Setters *************************/

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public List<Raca> getRacas() {
		return racas;
	}

	public void setRacas(List<Raca> racas) {
		this.racas = racas;
	}

	public Raca getRacaSelecionado() {
		return racaSelecionado;
	}

	public void setRacaSelecionado(Raca racaSelecionado) {
		this.racaSelecionado = racaSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
