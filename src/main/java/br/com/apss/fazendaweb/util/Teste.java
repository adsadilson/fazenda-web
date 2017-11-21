package br.com.apss.fazendaweb.util;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.attribute.standard.MediaSize.ISO;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGPoolingDataSource;

import br.com.apss.fazendaweb.model.Usuario;

public class Teste implements Serializable {

	private static final long serialVersionUID = 1L;

	private static EntityManager getEntityManager() {
		EntityManagerFactory factory = null;
		EntityManager entityManager = null;
		factory = Persistence.createEntityManagerFactory("FazendaPU");
		entityManager = factory.createEntityManager();
		return entityManager;
	}

	public static Usuario salvar(Usuario user) throws Exception {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			if (user.getId() == null) {
				entityManager.persist(user);
			} else {
				user = entityManager.merge(user);
			}
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		return user;
	}

	public static void excluir(Long id) {
		EntityManager entityManager = getEntityManager();
		try {
			// Inicia uma transação com o banco de dados.
			entityManager.getTransaction().begin();
			Usuario usuario = consultarPorId(id);
			entityManager.remove(usuario);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	public static Usuario consultarPorId(Long id) {
		EntityManager entityManager = getEntityManager();
		Usuario user = null;
		try {
			user = entityManager.find(Usuario.class, id);
		} finally {
			entityManager.close();
		}
		return user;
	}

	public static void main(String[] args) {
		
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("FazendaPU");
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.getTransaction().commit();
			entityManager.close();
			System.out.println("Operação realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Criação do DataSource
		PGPoolingDataSource dataSource = new PGPoolingDataSource();
		dataSource.setUser("postgres");
		dataSource.setPassword("postgres");
		dataSource.setDatabaseName("bdfazendaweb");
		dataSource.setInitialConnections(10);
		dataSource.setPortNumber(5433);
		dataSource.setServerName("localhost");
		 
		// Inicialição do FlyWay
		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource);
		flyway.setBaselineOnMigrate(true);
		flyway.setTable("version");
		flyway.setSqlMigrationPrefix("V");
		flyway.setSqlMigrationSeparator("_");
		flyway.setEncoding("ISO-8859-1");
		flyway.setValidateOnMigrate(true);
		 
		// executa Migração;
		flyway.migrate();
		
		

	}
}
