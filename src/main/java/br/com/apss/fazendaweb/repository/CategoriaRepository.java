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

import br.com.apss.fazendaweb.model.Categoria;
import br.com.apss.fazendaweb.util.NegocioException;


public class CategoriaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Categoria save(Categoria e) {
		return em.merge(e);
	}

	
	public void remove(Categoria categoria) {
		try {
			categoria = porId(categoria.getId());
			em.remove(categoria);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Categoria não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Categoria porId(Long value) {
		return em.find(Categoria.class, value);
	}

	public List<Categoria> listarTodos() {
		return em.createQuery("from Categoria order by nome", Categoria.class).getResultList();
	}

	public Categoria porNome(String nome) {
		try {
			return em.createQuery("from Categoria where nome = :nome", Categoria.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Categoria> grupoCondicao(Categoria op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Categoria.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
