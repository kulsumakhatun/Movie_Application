package com.niit.searchService.SearchService.controller;

import com.niit.searchService.SearchService.domain.Movie;
import com.niit.searchService.SearchService.services.MovieServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    Movie movie;
    List<String> list;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private MovieServiceImpl movieService;
    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList("Romantic");
        movie = new Movie(1, "hbcab","DDLJ", "12/01/2000", "test", new int[]{123}, "Hindi", "Nice", 4.5, list);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @AfterEach
    public void tearDown() {
        movie = null;
    }

    @Test
    public void searchMovieTest() throws Exception {
        when(movieService.searchMovieByName(any())).thenReturn(movie);
        mockMvc.perform(
                get("/api/search/DDLJ").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
        verify(movieService, times(1)).searchMovieByName(any());
    }
}
