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

import br.com.apss.fazendaweb.model.Especie;
import br.com.apss.fazendaweb.util.NegocioException;


public class EspecieRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Especie save(Especie e) {
		return em.merge(e);
	}

	
	public void remove(Especie especie) {
		try {
			especie = porId(especie.getId());
			em.remove(especie);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Especie não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Especie porId(Long value) {
		return em.find(Especie.class, value);
	}

	public List<Especie> listarTodos() {
		return em.createQuery("from Especie order by nome", Especie.class).getResultList();
	}

	public Especie porNome(String nome) {
		try {
			return em.createQuery("from Especie where nome = :nome", Especie.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Especie> grupoCondicao(Especie op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Especie.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
