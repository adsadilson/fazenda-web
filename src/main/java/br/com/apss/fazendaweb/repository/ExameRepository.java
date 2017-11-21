package br.com.apss.fazendaweb.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.apss.fazendaweb.model.Exame;
import br.com.apss.fazendaweb.util.NegocioException;


public class ExameRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Exame save(Exame e) {
		return em.merge(e);
	}

	
	public void remove(Exame Exame) {
		try {
			Exame = porId(Exame.getId());
			em.remove(Exame);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Exame não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Exame porId(Long value) {
		return em.find(Exame.class, value);
	}

	public List<Exame> listarTodos() {
		return em.createQuery("from Exame order by tipoExame", Exame.class).getResultList();
	}

	public Exame porNome(String nome) {
		try {
			return em.createQuery("from Exame where nome = :nome", Exame.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}
