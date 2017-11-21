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

import br.com.apss.fazendaweb.model.Pessoa;
import br.com.apss.fazendaweb.util.NegocioException;

public class PessoaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Pessoa save(Pessoa e) {
		return em.merge(e);
	}

	public void remove(Pessoa pessoa) {
		try {
			pessoa = porId(pessoa.getId());
			em.remove(pessoa);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Pessoa não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Pessoa porId(Long value) {
		System.out.println("repository " + value);
		return em.find(Pessoa.class, value);
	}

	public List<Pessoa> listarTodos() {
		return em.createQuery("from Pessoa order by nome", Pessoa.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarPorCondicao(Pessoa op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pessoa.class);

		if (op.getEmpresa()) {
			criteria.add(Restrictions.ge("empresa", true));
		}

		if (op.getCliente()) {
			criteria.add(Restrictions.ge("cliente", true));
		}

		if (op.getFornecedor()) {
			criteria.add(Restrictions.ge("fornecedor", true));
		}

		if (op.getProfissional()) {
			criteria.add(Restrictions.ge("profissional", true));
		}

		if (op.getFuncionario()) {
			criteria.add(Restrictions.ge("funcionario", true));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Pessoa porNome(String nome) {
		try {
			return em.createQuery("from Pessoa where nome = :nome", Pessoa.class).setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> grupoCondicao(Pessoa op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pessoa.class);

		if (op.getStatus().equals("ATIVO")) {
			criteria.add(Restrictions.ge("status", true));
		}
		if (op.getStatus().equals("INATIVO")) {
			criteria.add(Restrictions.le("status", false));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public List<Pessoa> listarProfissional(Boolean status) {
		return em.createQuery("FROM Pessoa WHERE profissional='true' AND status=:status ORDER BY nome", Pessoa.class)
				.setParameter("status", status).getResultList();
	}

}
