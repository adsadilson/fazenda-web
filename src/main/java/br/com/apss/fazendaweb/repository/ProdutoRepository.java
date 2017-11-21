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

import br.com.apss.fazendaweb.model.Produto;
import br.com.apss.fazendaweb.util.NegocioException;

public class ProdutoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Produto save(Produto e) {
		return em.merge(e);
	}

	public void remove(Produto produto) {
		try {
			produto = porId(produto.getId());
			em.remove(produto);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Produto porId(Long value) {
		System.out.println("repository " + value);
		return em.find(Produto.class, value);
	}

	public List<Produto> listarTodos() {
		return em.createQuery("from Produto order by nome", Produto.class).getResultList();
	}

	public Produto porNome(String nome) {
		try {
			return em.createQuery("from Produto where nome = :nome", Produto.class).setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Produto porCodigoBarra(String codigo_barra) {
		try {
			return em.createQuery("from Produto where codigo_barra = :codigo_barra", Produto.class)
					.setParameter("codigo_barra", codigo_barra).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> grupoCondicao(Produto op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
