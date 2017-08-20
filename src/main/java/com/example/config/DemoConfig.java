package com.example.config;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

@Profile("!test")
@Configuration
@EnableNeo4jRepositories(basePackages = "com.example.repositories")
public class DemoConfig {

	private String URL = "bolt://localhost:7687";
	
	@Bean("transactionManager")
	public Neo4jTransactionManager getTransactionManager(){
		

		return new Neo4jTransactionManager(getSessionFactory());
	}
	
	@Bean
	public SessionFactory getSessionFactory() {
	    return new SessionFactory(getConfiguration(), "com.example.bean");
	}
	
	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
	    org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
	    config.driverConfiguration().setDriverClassName(
	      "org.neo4j.ogm.drivers.bolt.driver.BoltDriver").setURI(URL).setCredentials("neo4j", "mardecopas");
	    return config;
	}
	
}
