package com.example.config;

import java.util.HashMap;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.TransactionManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.example.repositories")
public class DemoConfigTest {
	
	public DemoConfigTest() {
		System.out.println("DemoConfigTest");
	}

	
	@Bean("transactionManager")
	public Neo4jTransactionManager getTransactionManager(){
		SessionFactory sessionFactory = getSessionFactory();
		initialize(sessionFactory);
		return new Neo4jTransactionManager(sessionFactory);
	}
	
	@Bean
	public SessionFactory getSessionFactory() {
	    return new SessionFactory(getConfiguration(), "com.example.bean");
	}
	
	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
	    org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
	    config.driverConfiguration().setDriverClassName(
	      "org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");
	    return config;
	}
	
	public void initialize(SessionFactory sessionFactory){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.query("MERGE (p:Person{name:'Antonio'})", new HashMap<>());
		session.getTransaction().commit();
	}
	
}
