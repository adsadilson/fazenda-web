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

import br.com.apss.fazendaweb.model.Origem;
import br.com.apss.fazendaweb.util.NegocioException;


public class OrigemRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Origem save(Origem e) {
		return em.merge(e);
	}

	
	public void remove(Origem origem) {
		try {
			origem = porId(origem.getId());
			em.remove(origem);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Origem não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Origem porId(Long value) {
		return em.find(Origem.class, value);
	}

	public List<Origem> listarTodos() {
		return em.createQuery("from Origem order by nome", Origem.class).getResultList();
	}

	public Origem porNome(String nome) {
		try {
			return em.createQuery("from Origem where nome = :nome", Origem.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Origem> grupoCondicao(Origem op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Origem.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
