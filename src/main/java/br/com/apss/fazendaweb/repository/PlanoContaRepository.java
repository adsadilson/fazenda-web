package br.com.apss.fazendaweb.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.apss.fazendaweb.model.PlanoConta;
import br.com.apss.fazendaweb.model.filter.PlanoContaFilter;
import br.com.apss.fazendaweb.util.NegocioException;

public class PlanoContaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public PlanoConta save(PlanoConta e) {
		return em.merge(e);
	}

	public void remove(PlanoConta planoConta) {
		try {
			planoConta = porId(planoConta.getId());
			em.remove(planoConta);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Plano de Conta não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public PlanoConta porId(Long value) {
		return em.find(PlanoConta.class, value);
	}

	public List<PlanoConta> listarTodos() {
		return em.createQuery("from PlanoConta order by mascara", PlanoConta.class).getResultList();
	}

	public PlanoConta porNome(String mascara) {
		try {
			return em.createQuery("from PlanoConta where mascara = :mascara", PlanoConta.class)
					.setParameter("mascara", mascara).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlanoConta> grupoCondicao(PlanoConta op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(PlanoConta.class);

		if (op.getStatus()) {
			criteria.add(Restrictions.ge("status", true));
		}

		return criteria.addOrder(Order.asc("conta")).list();
	}

	@SuppressWarnings("unchecked")
	public List<PlanoConta> filtrados(PlanoContaFilter filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(PlanoConta.class);

		if (StringUtils.isNotBlank(filtro.getConta())) {
			criteria.add(Restrictions.ilike("conta", filtro.getConta(), MatchMode.ANYWHERE));
		}

		if (filtro.getContaPaiID() != null) {
			criteria.add(Restrictions.eq("contaPai", filtro.getContaPaiID()));
		}

		if (filtro.getAtivo() != null) {
			if (filtro.getAtivo().equals("ATIVO")) {
				criteria.add(Restrictions.ge("status", true));
			}
			if (filtro.getAtivo().equals("INATIVO")) {
				criteria.add(Restrictions.le("status", false));
			}
		}

		return criteria.addOrder(Order.asc("mascara")).list();
	}

}
