package com.niit.searchService.SearchService.services;

import com.niit.searchService.SearchService.domain.Movie;
import com.niit.searchService.SearchService.exception.MovieNotFoundException;

import java.util.List;

public interface MovieService {

    public Movie searchMovieByName(String name) throws MovieNotFoundException;

    public List<Movie> startsWithName(String name);
}
