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

import br.com.apss.fazendaweb.model.CausaMorte;
import br.com.apss.fazendaweb.util.NegocioException;


public class CausaMorteRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public CausaMorte save(CausaMorte e) {
		return em.merge(e);
	}

	
	public void remove(CausaMorte causaMorte) {
		try {
			causaMorte = porId(causaMorte.getId());
			em.remove(causaMorte);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Causa da Morte não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public CausaMorte porId(Long value) {
		return em.find(CausaMorte.class, value);
	}

	public List<CausaMorte> listarTodos() {
		return em.createQuery("from CausaMorte order by nome", CausaMorte.class).getResultList();
	}

	public CausaMorte porNome(String nome) {
		try {
			return em.createQuery("from CausaMorte where nome = :nome", CausaMorte.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CausaMorte> grupoCondicao(CausaMorte op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CausaMorte.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
