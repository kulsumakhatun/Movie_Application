package com.niit.MovieService.services;

import com.niit.MovieService.domain.Movie;
import com.niit.MovieService.exceptions.MovieAlreadyExistsException;
import com.niit.MovieService.exceptions.MovieNotFoundException;
import com.niit.MovieService.repository.MovieRepository;
import netscape.javascript.JSObject;
import org.apache.tomcat.util.json.JSONParser;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public List<Movie> loadPopularMovies(List<Movie> movieList) {
        for(int i = 0 ; i < movieList.size() ; i++){
            if(movieRepository.findById(movieList.get(i).getId()).isPresent()){
                Movie movie = movieRepository.findById(movieList.get(i).getId()).get();
                movie.getKeyWords().add("popular");
                movieRepository.save(movie);
            }else {
                movieList.get(i).setKeyWords(Arrays.asList("popular"));
                movieList.get(i).setRating(new HashMap<>());
                movieRepository.insert(movieList.get(i));
            }
        }
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> loadFreeMovies(List<Movie> freeMovieList) {
        for(int i = 0 ; i < freeMovieList.size() ; i++){
            if(movieRepository.findById(freeMovieList.get(i).getId()).isPresent()){
                Movie movie = movieRepository.findById(freeMovieList.get(i).getId()).get();
                movie.getKeyWords().add("free");
                movieRepository.save(movie);
            }else {
                freeMovieList.get(i).setKeyWords(Arrays.asList("free"));
                freeMovieList.get(i).setRating(new HashMap<>());
                movieRepository.insert(freeMovieList.get(i));
            }
        }

        return movieRepository.findAll();
    }

    @Override
    public List<Movie> loadTrendingMovies(List<Movie> trendingList) {
        for(int i = 0 ; i < trendingList.size() ; i++){
            if(movieRepository.findById(trendingList.get(i).getId()).isPresent()){
                 Movie movie = movieRepository.findById(trendingList.get(i).getId()).get();
                movie.getKeyWords().add("trending");
                movieRepository.save(movie);
            }else {
                trendingList.get(i).setKeyWords(Arrays.asList("trending"));
                trendingList.get(i).setRating(new HashMap<>());
                movieRepository.insert(trendingList.get(i));
            }
        }
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> updateMovieList(List<Movie> movieList) {
        movieRepository.deleteAll();
        movieRepository.insert(movieList);
        return movieRepository.findAll();
    }

    @Override
    public boolean deleteAllMovies() {
        movieRepository.deleteAll();
        return true;
    }


    @Override
    public Movie addMovie(Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.findById(movie.getId()).isPresent()){
            throw new MovieAlreadyExistsException();
        }else {
            return movieRepository.insert(movie);
        }
    }

    @Override
    public boolean deleteMovie(int movieId) throws MovieNotFoundException {
        if(movieRepository.findById(movieId).isEmpty()){
            throw new MovieNotFoundException();
        }else {
            movieRepository.deleteById(movieId);
            return true;
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public int getRatingOnMovieIdAndUserId(int id, String email) {
        String updatedEmail = email.replace(".","-");
        Movie movie = movieRepository.findById(id).get();
        Map<String,Integer> ratings = movie.getRating();
        int result = 0 ;
        if(ratings.get(updatedEmail) != null){
            result = ratings.get(updatedEmail);
        }
        return result ;
    }

    @Override
    public Map<String, Integer> addRating(int id, String email, int rating) {
        Movie movie = movieRepository.findById(id).get();
        Map<String, Integer> ratings = movie.getRating();
        String updatedEmail= email.replace(".","-");
        System.out.println(email+"replaced email");
        if(ratings.containsKey(email)){
            ratings.replace(updatedEmail,rating);
        }
        else {
            ratings.put(updatedEmail,rating);
        }
        movie.setRating(ratings);
        System.out.println(movie);
        movieRepository.save(movie);
        return ratings;
    }

}
