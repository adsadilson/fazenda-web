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

import br.com.apss.fazendaweb.model.GrupoUsuario;
import br.com.apss.fazendaweb.model.filter.GrupoUsuarioFilter;
import br.com.apss.fazendaweb.util.NegocioException;


public class GrupoUsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public GrupoUsuario save(GrupoUsuario e) {
		return em.merge(e);
	}

	
	public void remove(GrupoUsuario categoria) {
		try {
			categoria = porId(categoria.getId());
			em.remove(categoria);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Grupo de Usuario não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public GrupoUsuario porId(Long value) {
		System.out.println("repository "+value);
		return em.find(GrupoUsuario.class, value);
	}

	public List<GrupoUsuario> listarTodos() {
		return em.createQuery("from GrupoUsuario order by nome", GrupoUsuario.class).getResultList();
	}

	public GrupoUsuario porNome(String nome) {
		try {
			return em.createQuery("from GrupoUsuario where nome = :nome", GrupoUsuario.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoUsuario> filtrados(GrupoUsuarioFilter filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(GrupoUsuario.class);

		if (filtro.getIdDe() != null) {
			// id deve ser maior ou igual (ge = greater or equals) a
			// filtro.IdDe
			criteria.add(Restrictions.ge("id", filtro.getIdDe()));
		}

		if (filtro.getIdAte() != null) {
			// id deve ser menor ou igual (le = lower or equal) a
			// filtro.IdDe
			criteria.add(Restrictions.le("id", filtro.getIdAte()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (filtro.getAtivo().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (filtro.getAtivo().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoUsuario> grupoCondicao(GrupoUsuario op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(GrupoUsuario.class);

		if (op.getAtivo().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getAtivo().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}


}
