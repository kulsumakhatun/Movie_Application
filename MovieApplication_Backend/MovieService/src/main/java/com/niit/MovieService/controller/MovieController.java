package com.niit.MovieService.controller;

import com.niit.MovieService.domain.Movie;
import com.niit.MovieService.exceptions.MovieAlreadyExistsException;
import com.niit.MovieService.exceptions.MovieNotFoundException;
import com.niit.MovieService.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
@RestController
@RequestMapping("/movie/api/v1")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("popularMovies")
    public ResponseEntity<?> loadAllMovies(@RequestBody List<Movie> movieList){

        return new ResponseEntity<>(movieService.loadPopularMovies(movieList), HttpStatus.CREATED);

    }

    @PostMapping("freeMovies")
    public ResponseEntity<?> loadFreeMovies(@RequestBody List<Movie> movieList){
        return new ResponseEntity<>(movieService.loadFreeMovies(movieList),HttpStatus.CREATED);
    }
    @PostMapping("trendingMovies")
    public ResponseEntity<?> loadTrendingMovies(@RequestBody List<Movie> movieList){
        return new ResponseEntity<>(movieService.loadTrendingMovies(movieList),HttpStatus.CREATED);
    }

    @PostMapping("addMovie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie){
        try {
            return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);
        } catch (MovieAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id){
        try {
            return new ResponseEntity<>(movieService.deleteMovie(id),HttpStatus.ACCEPTED);
        } catch (MovieNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("updateMovieList")
    public ResponseEntity<?> updateMovieList(@RequestBody List<Movie> movieList){
        return new ResponseEntity<>(movieService.updateMovieList(movieList),HttpStatus.CREATED);
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<?> deleteAllMovies(){
        return new ResponseEntity<>(movieService.deleteAllMovies(),HttpStatus.ACCEPTED);
    }

    @GetMapping("allMovies")
    public ResponseEntity<?> getAllMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMovieById(@PathVariable int id){
        return new ResponseEntity<>(movieService.getMovieById(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("rating/{id}/{email}")
    public ResponseEntity<?> getRatingOfMovie(@PathVariable int id, @PathVariable String email){
        ResponseEntity responseEntity = null;
        int rating = movieService.getRatingOnMovieIdAndUserId(id,email);
        if(rating != 0){
            responseEntity = new ResponseEntity(rating,HttpStatus.ACCEPTED);
        }else{
            responseEntity = new ResponseEntity(null, HttpStatus.ACCEPTED);
        }
        return responseEntity;
    }

    @PostMapping("addrating/{id}/{rating}")
    public ResponseEntity<?> addRating(@PathVariable int id, @RequestBody String email, @PathVariable int rating){
        return new ResponseEntity<>(movieService.addRating(id,email,rating),HttpStatus.CREATED);
    }

}
