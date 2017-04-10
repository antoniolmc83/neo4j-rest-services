package com.example.bean;

import java.util.ArrayList;
import java.util.Collection;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="ACTED_IN")
public class Role {
	@GraphId
	private Long id;
	
	private Collection<String> roles = new ArrayList<>();
	
	@StartNode
	private Person person;
	
	@EndNode
	private Movie movie;

	public Role() {

	}
	
	public Role(Person person, Movie movie) {
		super();
		this.person = person;
		this.movie = movie;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<String> getRoles() {
		return roles;
	}

	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	
}
