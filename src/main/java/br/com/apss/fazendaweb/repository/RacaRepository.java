package br.com.apss.fazendaweb.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.apss.fazendaweb.model.Raca;
import br.com.apss.fazendaweb.util.NegocioException;


public class RacaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Raca save(Raca obj) {
		return em.merge(obj);
	}

	
	public void remove(Raca obj) {
		try {
			obj = porId(obj.getId());
			em.remove(obj);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Raça não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Raca porId(Long value) {
		return em.find(Raca.class, value);
	}

	public List<Raca> listarTodos() {
		return em.createQuery("from Raca order by nome", Raca.class).getResultList();
	}

	public Raca porNome(String nome) {
		try {
			return em.createQuery("from Raca where nome = :nome", Raca.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Raca> grupoCondicao(Raca op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Raca.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
