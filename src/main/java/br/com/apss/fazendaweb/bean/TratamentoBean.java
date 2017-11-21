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
import org.primefaces.context.RequestContext;

import br.com.apss.fazendaweb.enums.AtivoInativo;
import br.com.apss.fazendaweb.model.Tratamento;
import br.com.apss.fazendaweb.model.UnidadeMedida;
import br.com.apss.fazendaweb.model.Animal;
import br.com.apss.fazendaweb.model.TipoAplicacao;
import br.com.apss.fazendaweb.model.TipoTratamento;
import br.com.apss.fazendaweb.service.AnimalService;
import br.com.apss.fazendaweb.service.TipoAplicacaoService;
import br.com.apss.fazendaweb.service.TratamentoService;
import br.com.apss.fazendaweb.service.UnidadeMedidaService;
import br.com.apss.fazendaweb.service.TipoTratamentoService;
import br.com.apss.fazendaweb.util.FacesUtil;

@Named
@ViewScoped
public class TratamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Tratamento tratamento;
	private List<Tratamento> tratamentos = new ArrayList<>();
	private Tratamento tratamentoSelecionado;

	@Inject
	TratamentoService tratamentoService;

	@Inject
	AnimalService animalServic;

	@Inject
	TipoTratamentoService tipoTratamentoService;
	
	@Inject
	TipoAplicacaoService tipoAplicacaoService;
	
	@Inject
	UnidadeMedidaService unidadeService;
	

	/****************************** Metodos *************************/

	@PostConstruct
	public void inicializarBean() {
		if (FacesUtil.isNotPostback()) {
			carregarTabela();
		}
	}

	private void carregarTabela() {
		tratamentos = tratamentoService.listarTodos();
	}

	public TratamentoBean() {
	}

	public List<AtivoInativo> getAtivoInativo() {
		return Arrays.asList(AtivoInativo.values());
	}

	public void novoCadastro() {
		this.tratamento = new Tratamento();
	}

	public void salvar() {
		tratamentoService.salvar(this.tratamento);
		this.tratamentoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro salvo com sucesso");
		RequestContext request = RequestContext.getCurrentInstance();
		request.addCallbackParam("sucesso", true);
	}

	public void excluir() {
		tratamentoService.remover(this.tratamento);
		this.tratamentoSelecionado = null;
		carregarTabela();
		Messages.addGlobalInfo("Registro excluido com sucesso");
	}

	public void editar() {
		this.tratamento = tratamentoService.buscarPorId(tratamentoSelecionado.getId());
	}

	public List<Animal> getAnimals() {
		return animalServic.listarTodos();
	}

	public List<TipoTratamento> getTipoTratamentos() {
		return tipoTratamentoService.listarTodos();
	}

	public List<TipoAplicacao> getTipoAplicacoes() {
		return tipoAplicacaoService.listarTodos();
	}
	
	public List<UnidadeMedida> getUnidades() {
		return unidadeService.listarTodos();
	}

	/****************************** Getters e Setters *************************/

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public List<Tratamento> getTratamentos() {
		return tratamentos;
	}

	public void setTratamentos(List<Tratamento> tratamentos) {
		this.tratamentos = tratamentos;
	}

	public Tratamento getTratamentoSelecionado() {
		return tratamentoSelecionado;
	}

	public void setTratamentoSelecionado(Tratamento tratamentoSelecionado) {
		this.tratamentoSelecionado = tratamentoSelecionado;
	}

	/****************************** Getters e Setters *************************/
}
