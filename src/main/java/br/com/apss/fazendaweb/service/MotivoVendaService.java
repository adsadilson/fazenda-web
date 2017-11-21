package br.com.apss.fazendaweb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.apss.fazendaweb.model.MotivoVenda;
import br.com.apss.fazendaweb.repository.MotivoVendaRepository;
import br.com.apss.fazendaweb.util.NegocioException;
import br.com.apss.fazendaweb.util.Transactional;

public class MotivoVendaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MotivoVendaRepository motivoVendaRepository;

	@Transactional
	public MotivoVenda salvar(MotivoVenda motivoVenda) {
		MotivoVenda motivoVendaExistente = motivoVendaRepository.porNome(motivoVenda.getNome());

		if (motivoVendaExistente != null && !motivoVendaExistente.equals(motivoVenda)) {
			throw new NegocioException("Já existe uma MotivoVenda com esse nome informado.");
		}
		return motivoVendaRepository.save(motivoVenda);
	}

	@Transactional
	public void remover(MotivoVenda motivoVenda) {
		motivoVendaRepository.remove(motivoVenda);
	}

	public MotivoVenda buscarPorId(Long id) {
		return motivoVendaRepository.porId(id);
	}

	public List<MotivoVenda> listarTodos() {
		return motivoVendaRepository.listarTodos();
	}

	public List<MotivoVenda> grupoCondicao(MotivoVenda op) {
		return motivoVendaRepository.grupoCondicao(op);
		
	}

	public MotivoVenda porId(Long id) {
		return motivoVendaRepository.porId(id);
	}

}
