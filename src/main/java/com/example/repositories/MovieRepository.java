package com.example.repositories;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.bean.Movie;


public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>{

	Movie findByTitle(@Param("title") String title);
	
	Collection<Movie> findByTitleLike(@Param("title") String title);
	
	@Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT{limit}")
	Collection<Movie> graph(@Param("limit") int limit);
}
