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

import br.com.apss.fazendaweb.model.TipoAplicacao;
import br.com.apss.fazendaweb.util.NegocioException;


public class TipoAplicacaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public TipoAplicacao save(TipoAplicacao e) {
		return em.merge(e);
	}

	
	public void remove(TipoAplicacao TipoAplicacao) {
		try {
			TipoAplicacao = porId(TipoAplicacao.getId());
			em.remove(TipoAplicacao);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Tipo de Aplicação não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public TipoAplicacao porId(Long value) {
		return em.find(TipoAplicacao.class, value);
	}

	public List<TipoAplicacao> listarTodos() {
		return em.createQuery("from TipoAplicacao order by nome", TipoAplicacao.class).getResultList();
	}

	public TipoAplicacao porNome(String nome) {
		try {
			return em.createQuery("from TipoAplicacao where nome = :nome", TipoAplicacao.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TipoAplicacao> grupoCondicao(TipoAplicacao op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TipoAplicacao.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
