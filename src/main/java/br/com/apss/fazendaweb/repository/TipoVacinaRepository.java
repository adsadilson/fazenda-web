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

import br.com.apss.fazendaweb.model.TipoVacina;
import br.com.apss.fazendaweb.util.NegocioException;


public class TipoVacinaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public TipoVacina save(TipoVacina obj) {
		return em.merge(obj);
	}

	
	public void remove(TipoVacina obj) {
		try {
			obj = porId(obj.getId());
			em.remove(obj);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Tipo de Vacina não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public TipoVacina porId(Long value) {
		return em.find(TipoVacina.class, value);
	}

	public List<TipoVacina> listarTodos() {
		return em.createQuery("from TipoVacina order by nome", TipoVacina.class).getResultList();
	}

	public TipoVacina porNome(String nome) {
		try {
			return em.createQuery("from TipoVacina where nome = :nome", TipoVacina.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TipoVacina> grupoCondicao(TipoVacina op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TipoVacina.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
