package br.com.apss.fazendaweb.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.apss.fazendaweb.model.Tanque;
import br.com.apss.fazendaweb.util.NegocioException;


public class TanqueRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Tanque save(Tanque obj) {
		return em.merge(obj);
	}

	
	public void remove(Tanque obj) {
		try {
			obj = porId(obj.getId());
			em.remove(obj);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Tanque não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Tanque porId(Long value) {
		return em.find(Tanque.class, value);
	}

	public List<Tanque> listarTodos() {
		return em.createQuery("from Tanque order by nome", Tanque.class).getResultList();
	}

	public Tanque porNome(String nome) {
		try {
			return em.createQuery("from Tanque where nome = :nome", Tanque.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}
