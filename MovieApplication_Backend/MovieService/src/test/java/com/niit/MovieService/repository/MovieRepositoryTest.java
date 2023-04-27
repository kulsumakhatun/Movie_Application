package com.niit.MovieService.repository;

import com.niit.MovieService.domain.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class MovieRepositoryTest {


    @Autowired
    private MovieRepository movieRepository;

    private Movie movie;
    private Movie movie1;

    @BeforeEach
    public void setUp(){
        movieRepository.deleteAll();
        movie = new Movie(101,"Kantara", new String[]{"India"},"27-02-1999","jhbfvjhdsvy",new int[]{102,100,234},"Tamil","Inspired by the gripping true story of a man who would do anything for his family�and for freedom.",8.9,Arrays.asList("popular","trending"));
        movie1 = new Movie(105,"Twilight", new String[]{"US"},"1-09-2005","jhbfvjhdsvy",new int[]{102,100,234},"Tamil","Inspired by the gripping true story of a man who would do anything for his family�and for freedom.",8.9,Arrays.asList("popular","trending"));

    }



    @Test
    void saveMovieTest(){
        Movie movieObj = movieRepository.insert(movie);
        List<Movie> movieList = movieRepository.findAll();
        assertNotNull(movieList);
        assertEquals(1,movieList.size());


    }

    @Test
    void deleteDataTest(){
        movieRepository.insert(movie);
//        movieRepository.insert(movie1);
        movieRepository.deleteById(movie.getId());
        List<Movie> movieList = movieRepository.findAll();
        assertEquals(0,movieList.size());

    }

    @AfterEach
    public void tearDown(){
        movie = null;
        movie1=null ;
        movieRepository.deleteAll();
    }





}
