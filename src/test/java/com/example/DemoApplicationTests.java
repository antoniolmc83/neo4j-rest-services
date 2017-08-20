package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.web.rest.HelloWorldController;
import com.example.web.rest.Neo4jController;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes={DemoApplicationTests.class})
public class DemoApplicationTests {
	
	@Autowired
	private HelloWorldController helloWorldController;
	
	@Autowired
	private Neo4jController neo4jController;

	@Test
	public void contextLoads() {
		assertThat(helloWorldController).isNotNull();
		assertThat(neo4jController).isNotNull();

	}



	
}
