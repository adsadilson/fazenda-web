package br.com.apss.fazendaweb.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.apss.fazendaweb.model.Tratamento;
import br.com.apss.fazendaweb.util.NegocioException;


public class TratamentoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Tratamento save(Tratamento e) {
		return em.merge(e);
	}

	
	public void remove(Tratamento Tratamento) {
		try {
			Tratamento = porId(Tratamento.getId());
			em.remove(Tratamento);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Tratamento não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Tratamento porId(Long value) {
		return em.find(Tratamento.class, value);
	}

	public List<Tratamento> listarTodos() {
		return em.createQuery("from Tratamento order by tipoTratamento", Tratamento.class).getResultList();
	}

	public Tratamento porNome(String nome) {
		try {
			return em.createQuery("from Tratamento where nome = :nome", Tratamento.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}
