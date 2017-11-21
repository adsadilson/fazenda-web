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

import br.com.apss.fazendaweb.model.TipoTratamento;
import br.com.apss.fazendaweb.util.NegocioException;


public class TipoTratamentoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public TipoTratamento save(TipoTratamento obj) {
		return em.merge(obj);
	}

	
	public void remove(TipoTratamento obj) {
		try {
			obj = porId(obj.getId());
			em.remove(obj);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Tipo de Tratamento não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public TipoTratamento porId(Long value) {
		return em.find(TipoTratamento.class, value);
	}

	public List<TipoTratamento> listarTodos() {
		return em.createQuery("from TipoTratamento order by nome", TipoTratamento.class).getResultList();
	}

	public TipoTratamento porNome(String nome) {
		try {
			return em.createQuery("from TipoTratamento where nome = :nome", TipoTratamento.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TipoTratamento> grupoCondicao(TipoTratamento op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TipoTratamento.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
