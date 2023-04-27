package com.niit.FavouriteListService.services;

import com.niit.FavouriteListService.domain.Favourite;
import com.niit.FavouriteListService.domain.FavouriteList;
import com.niit.FavouriteListService.exception.FavouriteListAlreadyExists;
import com.niit.FavouriteListService.exception.MovieAlreadyExistsException;
import com.niit.MovieService.domain.Movie;
import java.util.List;

public interface FavouriteService {

    Favourite createFavouriteAccount(String email);
    Favourite getFavAccountByEmail(String email);
    Favourite addFavouriteList(String email, String favListName) throws FavouriteListAlreadyExists;

    Favourite addMovieToFavList(String email, String favListName, Movie movie) throws MovieAlreadyExistsException;
    Favourite deleteMovieFromFavList(String email,String favListName, int movieId);

    FavouriteList getFavListByName(String email, String favListName);
    Favourite deleteFavListByName(String email,String favListName);

    Favourite deleteFavAccBYEmail(String email);

    boolean deleteAllFavAcc();

    List<Favourite> getAllFavAcc();

}
