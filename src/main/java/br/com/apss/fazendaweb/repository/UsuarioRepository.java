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

import br.com.apss.fazendaweb.model.Usuario;
import br.com.apss.fazendaweb.model.filter.UsuarioFilter;
import br.com.apss.fazendaweb.util.NegocioException;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Usuario save(Usuario e) {
		return em.merge(e);
	}

	public void remove(Usuario categoria) {
		try {
			categoria = porId(categoria.getId());
			em.remove(categoria);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuário não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Usuario porId(Long value) {
		return em.find(Usuario.class, value);
	}

	public List<Usuario> listarTodos() {
		return em.createQuery("from Usuario order by nome", Usuario.class).getResultList();
	}

	public Usuario porNome(String nome) {
		try {
			return em.createQuery("from Usuario where nome = :nome", Usuario.class).setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario porNomeSenha(String nome, String senha) {
		try {
			return em.createQuery("from Usuario where nome = :nome and senha =:senha", Usuario.class)
					.setParameter("nome", nome).setParameter("senha", senha).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

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

}
