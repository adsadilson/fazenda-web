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

import br.com.apss.fazendaweb.model.UnidadeMedida;
import br.com.apss.fazendaweb.util.NegocioException;

public class UnidadeMedidaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public UnidadeMedida save(UnidadeMedida e) {
		return em.merge(e);
	}

	public void remove(UnidadeMedida unidadeMedida) {
		try {
			unidadeMedida = porId(unidadeMedida.getId());
			em.remove(unidadeMedida);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Unidade de Medida não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public UnidadeMedida porId(Long value) {
		return em.find(UnidadeMedida.class, value);
	}

	public List<UnidadeMedida> listarTodos() {
		return em.createQuery("from UnidadeMedida order by nome", UnidadeMedida.class).getResultList();
	}

	public UnidadeMedida porNome(String nome) {
		try {
			return em.createQuery("from UnidadeMedida where nome = :nome", UnidadeMedida.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UnidadeMedida> grupoCondicao(UnidadeMedida op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UnidadeMedida.class);

		if (op.getStatus()) {
			criteria.add(Restrictions.ge("status", true));
		} 

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
