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

import br.com.apss.fazendaweb.model.Animal;
import br.com.apss.fazendaweb.model.FichaAnimal;
import br.com.apss.fazendaweb.model.filter.FichaAnimalFilter;
import br.com.apss.fazendaweb.util.NegocioException;

public class FichaAnimalRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public FichaAnimal save(FichaAnimal e) {
		return em.merge(e);
	}

	public void remove(FichaAnimal fichaAnimal) {
		try {
			fichaAnimal = porId(fichaAnimal.getId());
			em.remove(fichaAnimal);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Registro não pode ser excluído pois possui vinculo com outras tabelas.");
		}
	}

	public FichaAnimal porId(Long value) {
		return em.find(FichaAnimal.class, value);
	}

	public List<FichaAnimal> listarTodos() {
		return em.createQuery("from FichaAnimal order by nome", FichaAnimal.class).getResultList();
	}

	public FichaAnimal porNome(String nome) {
		try {
			return em.createQuery("from FichaAnimal where nome = :nome", FichaAnimal.class).setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FichaAnimal> filtrados(FichaAnimalFilter filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FichaAnimal.class);

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
	public List<FichaAnimal> grupoCondicao(FichaAnimal op) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FichaAnimal.class);
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public List<FichaAnimal> porTipoLanc(String tipoLanc) {
		try {
			return em.createQuery("from FichaAnimal where tipo_lanc = :tipoLanc", FichaAnimal.class)
					.setParameter("tipoLanc", tipoLanc).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<FichaAnimal> carregarFichaParto() {
		try {
			return em.createQuery("from FichaAnimal where dt_parto is not null",
					FichaAnimal.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<FichaAnimal> carregarFichaSecagem() {
		try {
			return em.createQuery("from FichaAnimal where dt_secagem is not null",
					FichaAnimal.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<FichaAnimal> buscarPraParto() {
		String jpql = "SELECT f FROM FichaAnimal f INNER JOIN Animal a ON(f.animal.id=a.id) "
				+ "WHERE f.resultado ='POSITIVO' and f.dtParto is null ORDER BY a.nome";
		return em.createQuery(jpql, FichaAnimal.class).getResultList();
	}
	
	public List<FichaAnimal> buscarFichaSecagem() {
		String jpql = "SELECT f FROM FichaAnimal f INNER JOIN Animal a ON(f.animal.id=a.id) "
				+ "WHERE f.dtParto is not null and f.dtSecagem is null ORDER BY a.nome";
		return em.createQuery(jpql, FichaAnimal.class).getResultList();
	}

	public List<FichaAnimal> porAnimal(Animal animal) {
		try {
			return em.createQuery("from FichaAnimal where animal_id = :animal", FichaAnimal.class)
					.setParameter("animal", animal).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
