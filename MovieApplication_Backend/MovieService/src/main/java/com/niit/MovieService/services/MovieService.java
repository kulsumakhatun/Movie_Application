package com.niit.MovieService.services;

import com.niit.MovieService.domain.Movie;
import com.niit.MovieService.exceptions.MovieAlreadyExistsException;
import com.niit.MovieService.exceptions.MovieNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MovieService {

    List<Movie> loadPopularMovies(List<Movie> list) ;

    List<Movie> loadFreeMovies(List<Movie> freeMovieList);

    List<Movie> loadTrendingMovies(List<Movie> trendingList);


    List<Movie> updateMovieList(List<Movie> movieList);

    boolean deleteAllMovies();

    Movie addMovie(Movie movie) throws MovieAlreadyExistsException;
    boolean deleteMovie(int movieId) throws MovieNotFoundException;
    List<Movie> getAllMovies() ;

    Movie getMovieById(int id);

    int getRatingOnMovieIdAndUserId(int id,String email);

    Map<String,Integer> addRating(int id, String email, int rating);




}
