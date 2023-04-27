package com.niit.searchService.SearchService.repository;

import com.niit.searchService.SearchService.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer> {

    public Movie findByTitle(String name);
}
