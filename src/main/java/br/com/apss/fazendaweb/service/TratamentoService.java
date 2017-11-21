package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Tratamento;
import br.com.apss.fazendaweb.repository.TratamentoRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class TratamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TratamentoRepository TratamentoRepository;

	@Transactional
	public Tratamento salvar(Tratamento Tratamento) {
		/*Tratamento TratamentoExistente = TratamentoRepository.porNome(Tratamento.getNome());

		if (TratamentoExistente != null && !TratamentoExistente.equals(Tratamento)) {
			throw new NegocioException("Já existe uma Tratamento com esse nome informado.");
		}*/
		return TratamentoRepository.save(Tratamento);
	}

	@Transactional
	public void remover(Tratamento Tratamento) {
		TratamentoRepository.remove(Tratamento);
	}

	public Tratamento buscarPorId(Long id) {
		return TratamentoRepository.porId(id);
	}

	public List<Tratamento> listarTodos() {
		return TratamentoRepository.listarTodos();
	}

	public Tratamento porId(Long id) {
		return TratamentoRepository.porId(id);
	}

}
