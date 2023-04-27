//package com.niit.MovieService.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.niit.MovieService.domain.Movie;
//import com.niit.MovieService.exceptions.MovieAlreadyExistsException;
//import com.niit.MovieService.services.MovieServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class MovieControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private MovieServiceImpl movieService;
//
//    @InjectMocks
//    private MovieController movieController;
//
//    private Movie movie1, movie2, movie3;
//    private List<Movie> movieList;
//
//    @BeforeEach
//    void setUp(){
//        movie1 = new Movie(115,"Captain America", "usa","02-12-2022","jhbfvjhdsvy",new int[]{102,100,234},"English","Inspired by the gripping true story of a man who would do anything for his family�and for freedom.",8.9,Arrays.asList("popular"));
//        movie2 = new Movie(109,"Iron-Man", "jhhh","02-12-2022","jhbfvjhdsvy",new int[]{102,100,234},"English","Inspired by the gripping true story of a man who would do anything for his family�and for freedom.",8.9, Arrays.asList("popular"));
//        movie3 = new Movie(101,"Sholay", "jhvvh","02-12-2022","jhbfvjhdsvy",new int[]{102,100,234},"English","Inspired by the gripping true story of a man who would do anything for his family�and for freedom.",8.9, Arrays.asList("popular"));
//        movieList = Arrays.asList(movie1,movie2,movie3);
//        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
//
//    }
//
//    @AfterEach
//    void tearDown(){
//        movie1 = null;
//        movie2 = null ;
//        movie3 = null;
//        movieList = null;
//    }
//
//    private String jsonToString(Object object) throws JsonProcessingException {
//        ObjectMapper obj = new ObjectMapper();
//        String result = obj.writeValueAsString(object);
//        return result;
//    }
//
//    @Test
//    void loadPopularMoviesTest() throws Exception {
//        when(movieService.loadPopularMovies(any())).thenReturn(Arrays.asList(movie1));
//
//        mockMvc.perform(post("/movie/addpopularmovies")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonToString(Arrays.asList(movie1))))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//        assertEquals(Arrays.asList(movie1),movieService.loadPopularMovies(Arrays.asList(movie1)));
//        verify(movieService, times(2)).loadPopularMovies(any());
//    }
//
//    @Test
//    void loadFreeMoviesTest() throws Exception {
//        when(movieService.loadFreeMovies(any())).thenReturn(movieList);
//
//        mockMvc.perform(post("/movie/addfreemovies")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonToString(movieList)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//        assertEquals(movieList,movieService.loadFreeMovies(movieList));
//        verify(movieService, times(2)).loadFreeMovies(any());
//    }
//
//    @Test
//    void loadTrendingMoviesTest() throws Exception {
//        when(movieService.loadTrendingMovies(any())).thenReturn(movieList);
//        mockMvc.perform(post("/movie/addtrendingmovies")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonToString(movieList)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//        assertEquals(movieList, movieService.loadTrendingMovies(movieList));
//        verify(movieService,times(2)).loadTrendingMovies(any());
//    }
//
//    @Test
//    void deleteAllMoviesTest() throws Exception {
//        when(movieService.deleteAllMovies()).thenReturn(true);
//
//        mockMvc.perform(delete("/movie/deleteAll")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isAccepted())
//                .andDo(MockMvcResultHandlers.print());
//
//        assertEquals(true,movieService.deleteAllMovies());
//        verify(movieService,times(2)).deleteAllMovies();
//    }
//
//
//
//
//}
