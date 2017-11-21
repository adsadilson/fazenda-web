package br.com.apss.fazendaweb.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.apss.fazendaweb.model.Animal;
import br.com.apss.fazendaweb.model.FichaAnimal;
import br.com.apss.fazendaweb.util.NegocioException;

public class AnimalRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public Animal save(Animal e) {
		return em.merge(e);
	}

	public void remove(Animal animal) {
		try {
			animal = porId(animal.getId());
			em.remove(animal);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Animal não pode ser excluído pois possui vinculo com outra tabela.");
		}
	}

	public Animal porId(Long value) {
		return em.find(Animal.class, value);
	}

	public List<Animal> listarTodos() {
		return em.createQuery("from Animal order by nome", Animal.class).getResultList();
	}

	public Animal porNome(String nome) {
		try {
			return em.createQuery("from Animal where nome = :nome", Animal.class).setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Animal> porCategoria(String categoria) {
		return em.createQuery(
				"from Animal a inner join Categoria c on(a.id=c.categoria_id) " + "where c.nome = :categoria",
				Animal.class).setParameter("c.nome", categoria).getResultList();
	}

	public List<Animal> buscarAnimalCobertura() {
		String jpql = "SELECT a FROM Animal a INNER JOIN Categoria c ON(a.categoria.id=c.id) "
				+ "WHERE c.nome IN ('VACA','NOVILHA') AND c.status='true' ORDER BY a.nome";
		return em.createQuery(jpql, Animal.class).getResultList();
	}
	
	public List<Animal> buscarPraParto() {
		String jpql = "SELECT a FROM Animal a INNER JOIN FichaAnimal f ON(f.animal.id=a.id) "
				+ "WHERE f.resultado ='POSITIVO' and f.dtParto is null ORDER BY a.nome";
			return em.createQuery(jpql,Animal.class).getResultList();
	}
	
	
	public List<Animal> buscarAnimalReprodutor() {
		String jpql = "SELECT a FROM Animal a INNER JOIN Categoria c ON(a.categoria.id=c.id) "
				+ "WHERE c.nome IN ('REPRODUTOR') AND c.status='true' ORDER BY a.nome";
		return em.createQuery(jpql, Animal.class).getResultList();
	}

}
