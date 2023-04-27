package com.niit.userService.UserService.models;
import com.niit.MovieService.domain.Movie;
import org.springframework.data.annotation.Id;

import java.util.List;

public class FavouriteList {

    @Id
    private String favListName;
    private List<Movie> movieList;

    public FavouriteList() {
    }

    public FavouriteList(String favListName, List<Movie> movieList) {
        this.favListName = favListName;
        this.movieList = movieList;
    }

    public String getFavListName() {
        return favListName;
    }

    public void setFavListName(String favListName) {
        this.favListName = favListName;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public String toString() {
        return "FavouriteList{" +
                "favListName='" + favListName + '\'' +
                ", movieList=" + movieList +
                '}';
    }
}
