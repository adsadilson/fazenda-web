package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Tanque;
import br.com.apss.fazendaweb.repository.TanqueRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class TanqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TanqueRepository TanqueRepository;

	@Transactional
	public Tanque salvar(Tanque tanque) {
		Tanque TanqueExistente = TanqueRepository.porNome(tanque.getNome());

		if (TanqueExistente != null && !TanqueExistente.equals(tanque)) {
			throw new NegocioException("Já existe uma Tanque com esse nome informado.");
		}
		return TanqueRepository.save(tanque);
	}

	@Transactional
	public void remover(Tanque tanque) {
		TanqueRepository.remove(tanque);
	}

	public Tanque buscarPorId(Long id) {
		return TanqueRepository.porId(id);
	}

	public List<Tanque> listarTodos() {
		return TanqueRepository.listarTodos();
	}

	public Tanque porId(Long id) {
		return TanqueRepository.porId(id);
	}

}
