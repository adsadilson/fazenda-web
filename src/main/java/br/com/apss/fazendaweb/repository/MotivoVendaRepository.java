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

import br.com.apss.fazendaweb.model.MotivoVenda;
import br.com.apss.fazendaweb.util.NegocioException;


public class MotivoVendaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public MotivoVenda save(MotivoVenda e) {
		return em.merge(e);
	}

	
	public void remove(MotivoVenda motivoVenda) {
		try {
			motivoVenda = porId(motivoVenda.getId());
			em.remove(motivoVenda);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Motivo da Venda não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public MotivoVenda porId(Long value) {
		return em.find(MotivoVenda.class, value);
	}

	public List<MotivoVenda> listarTodos() {
		return em.createQuery("from MotivoVenda order by nome", MotivoVenda.class).getResultList();
	}

	public MotivoVenda porNome(String nome) {
		try {
			return em.createQuery("from MotivoVenda where nome = :nome", MotivoVenda.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MotivoVenda> grupoCondicao(MotivoVenda op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(MotivoVenda.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
