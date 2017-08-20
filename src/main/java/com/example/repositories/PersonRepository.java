package com.example.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	Person findByName(String name);
}
