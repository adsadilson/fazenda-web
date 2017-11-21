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

import br.com.apss.fazendaweb.model.TipoParto;
import br.com.apss.fazendaweb.util.NegocioException;


public class TipoPartoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public TipoParto save(TipoParto obj) {
		return em.merge(obj);
	}

	
	public void remove(TipoParto obj) {
		try {
			obj = porId(obj.getId());
			em.remove(obj);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Tipo de Parto não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public TipoParto porId(Long value) {
		return em.find(TipoParto.class, value);
	}

	public List<TipoParto> listarTodos() {
		return em.createQuery("from TipoParto order by nome", TipoParto.class).getResultList();
	}

	public TipoParto porNome(String nome) {
		try {
			return em.createQuery("from TipoParto where nome = :nome", TipoParto.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TipoParto> grupoCondicao(TipoParto op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TipoParto.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
