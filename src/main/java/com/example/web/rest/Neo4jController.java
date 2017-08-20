package com.example.web.rest;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bean.Person;
import com.example.repositories.MovieRepository;
import com.example.repositories.PersonRepository;

@Controller
@RequestMapping("/")
public class Neo4jController {


	private final Logger logger = LoggerFactory.getLogger(Neo4jController.class);
//	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private MovieRepository movieRepository;

	public Neo4jController(@Autowired PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/persons/{id}")
	@ResponseBody
	public Person getPersonById(@PathVariable Long id){
		Person resp = null;
		logger.info("Id: " + id);
		resp = personRepository.findOne(id);
		
		return resp;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/persons/name/{name}")
	@ResponseBody
	public Person getPersonByName(@PathVariable String name){
		Person resp = null;
		logger.info("Id: " + name);
		resp = personRepository.findByName(name);
		
		return resp;
	}
	
}
