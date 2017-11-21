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

import br.com.apss.fazendaweb.model.GrupoProduto;
import br.com.apss.fazendaweb.util.NegocioException;


public class GrupoProdutoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public GrupoProduto save(GrupoProduto e) {
		return em.merge(e);
	}

	
	public void remove(GrupoProduto categoria) {
		try {
			categoria = porId(categoria.getId());
			em.remove(categoria);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Grupo de Usuario não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public GrupoProduto porId(Long value) {
		System.out.println("repository "+value);
		return em.find(GrupoProduto.class, value);
	}

	public List<GrupoProduto> listarTodos() {
		return em.createQuery("from GrupoProduto order by nome", GrupoProduto.class).getResultList();
	}

	public GrupoProduto porNome(String nome) {
		try {
			return em.createQuery("from GrupoProduto where nome = :nome", GrupoProduto.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GrupoProduto> grupoCondicao(GrupoProduto op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(GrupoProduto.class);

		if (op.getStatus()) {
			criteria.add(Restrictions.ge("status", true));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
	

}
