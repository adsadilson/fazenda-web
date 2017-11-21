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

import br.com.apss.fazendaweb.model.CondicaoCorporal;
import br.com.apss.fazendaweb.util.NegocioException;

public class CondicaoCorporalRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public CondicaoCorporal save(CondicaoCorporal e) {
		return em.merge(e);
	}

	public void remove(CondicaoCorporal condicaoCorporal) {
		try {
			condicaoCorporal = porId(condicaoCorporal.getId());
			em.remove(condicaoCorporal);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Condicao Corporal não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public CondicaoCorporal porId(Long value) {
		return em.find(CondicaoCorporal.class, value);
	}

	public List<CondicaoCorporal> listarTodos() {
		return em.createQuery("from CondicaoCorporal order by nome", CondicaoCorporal.class).getResultList();
	}

	public List<CondicaoCorporal> listarTodos(Boolean status) {
		return em.createQuery("FROM CondicaoCorporal WHERE status=:status ORDER BY nome", CondicaoCorporal.class)
				.setParameter("status", status).getResultList();
	}

	public CondicaoCorporal porNome(String nome) {
		try {
			return em.createQuery("from CondicaoCorporal where nome = :nome", CondicaoCorporal.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CondicaoCorporal> grupoCondicao(CondicaoCorporal op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CondicaoCorporal.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
