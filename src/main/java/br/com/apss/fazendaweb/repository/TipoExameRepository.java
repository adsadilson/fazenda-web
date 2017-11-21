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

import br.com.apss.fazendaweb.model.TipoExame;
import br.com.apss.fazendaweb.util.NegocioException;


public class TipoExameRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public TipoExame save(TipoExame obj) {
		return em.merge(obj);
	}

	
	public void remove(TipoExame obj) {
		try {
			obj = porId(obj.getId());
			em.remove(obj);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Tipo de Exame não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public TipoExame porId(Long value) {
		return em.find(TipoExame.class, value);
	}

	public List<TipoExame> listarTodos() {
		return em.createQuery("from TipoExame order by nome", TipoExame.class).getResultList();
	}

	public TipoExame porNome(String nome) {
		try {
			return em.createQuery("from TipoExame where nome = :nome", TipoExame.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TipoExame> grupoCondicao(TipoExame op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TipoExame.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
