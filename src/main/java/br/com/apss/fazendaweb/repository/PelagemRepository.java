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

import br.com.apss.fazendaweb.model.Pelagem;
import br.com.apss.fazendaweb.util.NegocioException;


public class PelagemRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Pelagem save(Pelagem e) {
		return em.merge(e);
	}

	
	public void remove(Pelagem pelagem) {
		try {
			pelagem = porId(pelagem.getId());
			em.remove(pelagem);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Pelagem não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Pelagem porId(Long value) {
		return em.find(Pelagem.class, value);
	}

	public List<Pelagem> listarTodos() {
		return em.createQuery("from Pelagem order by nome", Pelagem.class).getResultList();
	}

	public Pelagem porNome(String nome) {
		try {
			return em.createQuery("from Pelagem where nome = :nome", Pelagem.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Pelagem> grupoCondicao(Pelagem op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pelagem.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
