package com.niit.FavouriteListService.repositorylayer;

import com.niit.FavouriteListService.domain.Favourite;
import com.niit.FavouriteListService.domain.FavouriteList;
import com.niit.FavouriteListService.repository.FavouriteRepository;
import com.niit.MovieService.domain.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class FavouriteRepositoryLayer {

    @Autowired
    private FavouriteRepository favouriteRepository;

    private Favourite favourite;
    private FavouriteList favouriteList;
    private Movie movie;

    @BeforeEach
    void setUp() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1,"Movie1"));
        favouriteList = new FavouriteList("Fav1",movieList);
            List<FavouriteList> favouriteLists = new ArrayList<>();
            favouriteLists.add(favouriteList);
        favourite = new Favourite("Test@1234",favouriteLists);
    }

    @AfterEach
    void tearDown() {
        favouriteRepository.deleteAll();
        favouriteList = null;
        favourite = null;
    }

    @Test
    public void createFavouriteAccount(){
        favouriteRepository.insert(favourite);
      Favourite favourite1 = favouriteRepository.findById("Test@1234").get();
      assertNotNull(favourite1);
      assertEquals(favourite1.getEmail(),favourite.getEmail());
    }


    @Test
    public void getFavouriteAccount(){
        favouriteRepository.insert(new Favourite("Test@1234",new ArrayList<>()));
        Optional<Favourite> favourite1 = favouriteRepository.findById("Test@1234");
        assertEquals(true,favourite1.isPresent());
    }

    @Test
    public void findAllFavouriteList(){
        favouriteRepository.insert(favourite);
        Favourite fav = new Favourite("Test2@gmail.com",new ArrayList<>());
        favouriteRepository.insert(fav);
       List<Favourite> allFavouriteList = favouriteRepository.findAll();
       assertEquals(2,allFavouriteList.size());
    }

    @Test
    public void deleteFavList(){
        favouriteRepository.save(favourite);
        favouriteRepository.deleteById("Test@1234");
        List<Favourite> allFavouriteList = favouriteRepository.findAll();
        assertEquals(0,allFavouriteList.size());

    }

    @Test
    public void addMovieToFavList(){
        Favourite favouriteList1 = favouriteRepository.insert(favourite);
        assertEquals("Movie1",favouriteList1.getFavouriteLists().get(0).getMovieList().get(0).getTitle());
    }

}
