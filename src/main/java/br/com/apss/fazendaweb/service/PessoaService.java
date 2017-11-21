package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Pessoa;
import br.com.apss.fazendaweb.repository.PessoaRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class PessoaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaRepository pessoaRepository;

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		Pessoa pessoaExistente = pessoaRepository.porNome(pessoa.getNome());

		if (pessoaExistente != null && !pessoaExistente.equals(pessoa)) {
			throw new NegocioException("Já existe uma Pessoa com esse nome informado.");
		}
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public void remover(Pessoa pessoa) {
		pessoaRepository.remove(pessoa);
	}

	public Pessoa buscarPorId(Long id) {
		return pessoaRepository.porId(id);
	}

	public List<Pessoa> listarTodos() {
		return pessoaRepository.listarTodos();
	}
	
	public List<Pessoa> listarPorCondicao(Pessoa op) {
		return pessoaRepository.listarPorCondicao(op);
		
	}

	public List<Pessoa> grupoCondicao(Pessoa op) {
		return pessoaRepository.grupoCondicao(op);
		
	}

	public Pessoa porId(Long id) {
		return pessoaRepository.porId(id);
	}
	
	public List<Pessoa> listarProfissional(Boolean status) {
		return pessoaRepository.listarProfissional(status);
		
	}

}
