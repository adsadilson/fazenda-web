package br.com.apss.fazendaweb.util;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGPoolingDataSource;

public class FlywayInit {

	PGPoolingDataSource dataSource = new PGPoolingDataSource();
	Flyway flyway = new Flyway();

	public void iniFlywa() {
		// Criação do DataSource
		PGPoolingDataSource dataSource = new PGPoolingDataSource();
		dataSource.setUser("dcicwsqthbdigy");
		dataSource.setPassword("1c5d25f0bbcf005b5c97ad1c621d929c4f33b1283a8871f98fb301b58a673f33");
		dataSource.setDatabaseName("d6uqsehfasgdg9");
		dataSource.setInitialConnections(10);
		dataSource.setPortNumber(5432);
		dataSource.setServerName("ec2-54-83-19-82.compute-1.amazonaws.com");
		 
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
		System.out.println("carregou o flyway...");
	}

	public DataSource dataSource() {
		dataSource.setUser("postgres");
		dataSource.setPassword("postgres");
		dataSource.setDatabaseName("bdfazendaweb");
		dataSource.setInitialConnections(10);
		dataSource.setPortNumber(5433);
		dataSource.setServerName("localhost");
		return dataSource;
	}

	public void confgFlywar() {
		flyway.setDataSource(dataSource());
		flyway.setBaselineOnMigrate(true);
		flyway.setTable("version");
		flyway.setSqlMigrationPrefix("V");
		flyway.setSqlMigrationSeparator("_");
		flyway.setEncoding("ISO-8859-1");
		flyway.setValidateOnMigrate(true);
	}
}
