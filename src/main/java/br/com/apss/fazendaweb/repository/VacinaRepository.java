package br.com.apss.fazendaweb.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.apss.fazendaweb.model.Vacina;
import br.com.apss.fazendaweb.util.NegocioException;


public class VacinaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Vacina save(Vacina obj) {
		return em.merge(obj);
	}

	
	public void remove(Vacina obj) {
		try {
			obj = porId(obj.getId());
			em.remove(obj);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Vacina não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Vacina porId(Long value) {
		return em.find(Vacina.class, value);
	}

	public List<Vacina> listarTodos() {
		return em.createQuery("from Vacina order by tipoVacina", Vacina.class).getResultList();
	}

	public Vacina porNome(String nome) {
		try {
			return em.createQuery("from Vacina where nome = :nome", Vacina.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}
