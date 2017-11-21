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

import br.com.apss.fazendaweb.model.TipoSecagem;
import br.com.apss.fazendaweb.util.NegocioException;


public class TipoSecagemRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public TipoSecagem save(TipoSecagem obj) {
		return em.merge(obj);
	}

	
	public void remove(TipoSecagem obj) {
		try {
			obj = porId(obj.getId());
			em.remove(obj);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Tipo de Secagem não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public TipoSecagem porId(Long value) {
		return em.find(TipoSecagem.class, value);
	}

	public List<TipoSecagem> listarTodos() {
		return em.createQuery("from TipoSecagem order by nome", TipoSecagem.class).getResultList();
	}

	public TipoSecagem porNome(String nome) {
		try {
			return em.createQuery("from TipoSecagem where nome = :nome", TipoSecagem.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TipoSecagem> grupoCondicao(TipoSecagem op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TipoSecagem.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
