package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.Vacina;
import br.com.apss.fazendaweb.repository.VacinaRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class VacinaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VacinaRepository VacinaRepository;

	@Transactional
	public Vacina salvar(Vacina Vacina) {
		/*Vacina VacinaExistente = VacinaRepository.porNome(Vacina.getNome());

		if (VacinaExistente != null && !VacinaExistente.equals(Vacina)) {
			throw new NegocioException("Já existe uma Vacina com esse nome informado.");
		}*/
		return VacinaRepository.save(Vacina);
	}

	@Transactional
	public void remover(Vacina Vacina) {
		VacinaRepository.remove(Vacina);
	}

	public Vacina buscarPorId(Long id) {
		return VacinaRepository.porId(id);
	}

	public List<Vacina> listarTodos() {
		return VacinaRepository.listarTodos();
	}

	public Vacina porId(Long id) {
		return VacinaRepository.porId(id);
	}

}
