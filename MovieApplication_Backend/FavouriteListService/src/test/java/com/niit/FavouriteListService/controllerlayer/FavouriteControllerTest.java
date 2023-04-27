package com.niit.FavouriteListService.controllerlayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.FavouriteListService.controller.FavouriteServiceController;
import com.niit.FavouriteListService.domain.Favourite;
import com.niit.FavouriteListService.domain.FavouriteList;
import com.niit.FavouriteListService.services.FavouriteServiceImpl;
import com.niit.MovieService.domain.Movie;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FavouriteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private FavouriteServiceImpl favouriteService;

    @InjectMocks
    private FavouriteServiceController favouriteController;

    private Favourite favourite;
    private FavouriteList favouriteList;
    private Movie movie;

    @BeforeEach
    void setUp() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1,"Movie3",new String[]{"IND"},"22-2-1994","skhfcbn",new int[]{123,234},"Eng","jkdcbhjhdce",8.9, Arrays.asList("popular")));
        favouriteList = new FavouriteList("Fav3",movieList);
        List<FavouriteList> favouriteLists = new ArrayList<>();
        favouriteLists.add(favouriteList);
        favourite = new Favourite("Test@1233",favouriteLists);
        mockMvc = MockMvcBuilders.standaloneSetup(favouriteController).build();
    }

    @AfterEach
    void tearDown() {
        favouriteList = null;
        favourite = null;
    }

    @Test
    public void createFavAccount() throws Exception {
        when(favouriteService.createFavouriteAccount(any())).thenReturn(favourite);
        mockMvc.perform(post("/favourite/favAcc/Test@1233")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(favourite)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(favouriteService,times(1)).createFavouriteAccount(any());
    }

    @Test
    public void deleteFavAccount() throws Exception {
            when(favouriteService.createFavouriteAccount(favourite.getEmail())).thenReturn(favourite);
           // favouriteService.createFavouriteAccount(favourite.getEmail());
        mockMvc.perform(post("/favourite/favAcc/Test@1233")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(favourite)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        when(favouriteService.deleteFavAccBYEmail(any())).thenReturn(favourite);
       // when(favouriteService.deleteFavAccBYEmail(favourite.getEmail())).thenReturn(true);
        mockMvc.perform(delete("/favourite/favList/deleteFavAcc/Test@1233")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
        verify(favouriteService,times(1)).deleteFavAccBYEmail(favourite.getEmail());
    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }
        return result;
    }
}
