package com.niit.MovieService.services;

import com.niit.MovieService.domain.Movie;
import com.niit.MovieService.exceptions.MovieAlreadyExistsException;
import com.niit.MovieService.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie1, movie2;
    private List<Movie> movieList;

    @BeforeEach
    public void setUp(){
        movie1 = new Movie(102,"Kesariyaan", "usa","08-10-2022","jhbfvjhdsvy",new int[]{102,100,234},"Hindi","Inspired by the gripping true story of a man who would do anything for his family�and for freedom.",8.9, Arrays.asList("popular","trending"));
        movie2 = new Movie(109,"Iron-Man", "ncvn","02-12-2022","jhbfvjhdsvy",new int[]{102,100,234},"English","Inspired by the gripping true story of a man who would do anything for his family�and for freedom.",8.9,Arrays.asList("popular"));
        List<Movie> movieList = Arrays.asList(movie1,movie2);
    }

    @AfterEach
    public void tearDown(){
        movie1=null;
        movie2=null;
        movieList = null;
    }

    @Test
    void loadPopularMoviesTest(){
        when(movieRepository.insert(movie1)).thenReturn(movie1);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1));
        assertEquals(Arrays.asList(movie1),movieService.loadPopularMovies(Arrays.asList(movie1)));
        verify(movieRepository, times(1)).insert(movie1);
    }

    @Test
    void loadFreeMovieTest(){
        when(movieRepository.insert(movie1)).thenReturn(movie1);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1));
        assertEquals(1,movieService.loadFreeMovies(Arrays.asList(movie1)).size());
        verify(movieRepository, times(1)).insert(movie1);
    }

    @Test
    void loadTrendingMovieTest(){
        when(movieRepository.insert(movie2)).thenReturn(movie2);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie2));
        assertEquals(Arrays.asList(movie2),movieService.loadTrendingMovies(Arrays.asList(movie2)));
        verify(movieRepository, times(1)).insert(movie2);
    }
    @Test
    void addMovieTest() throws MovieAlreadyExistsException {
        when(movieRepository.findById(movie2.getId())).thenReturn(Optional.ofNullable(null));
        when(movieRepository.insert(movie2)).thenReturn(movie2);
        assertEquals(movie2,movieService.addMovie(movie2));
        verify(movieRepository, times(1)).insert(movie2);
        verify(movieRepository, times(1)).findById(any());
    }

    @Test
    void addMovieFailureTest() throws MovieAlreadyExistsException {
        when(movieRepository.findById(movie2.getId())).thenReturn(Optional.ofNullable(movie2));
        assertThrows(MovieAlreadyExistsException.class,()-> movieService.addMovie(movie2));
        verify(movieRepository, times(1)).findById(any());
    }



}
