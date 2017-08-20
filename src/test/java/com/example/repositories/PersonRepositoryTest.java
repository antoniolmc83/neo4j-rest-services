package com.example.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.web.rest.Neo4jController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {
	

	
	@Autowired
	private PersonRepository personRepository;

	
//	@Before
//	public void initialize(){
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.query("MERGE (p:Person{name:'Antonio'})", new HashMap<>());
//		session.getTransaction().commit();
//		//session.getTransaction().close();
//	}
	
	@Test
	public void contextLoads() {
//		assertThat(helloWorldController).isNotNull();
//		assertThat(neo4jController).isNotNull();
		
		
		assertThat(personRepository.findByName("Antonio")).isNotNull();
	}
	
	
}
