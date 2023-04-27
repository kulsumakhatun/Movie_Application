package com.niit.FavouriteListService.services;

import com.niit.FavouriteListService.domain.Favourite;
import com.niit.FavouriteListService.domain.FavouriteList;
import com.niit.FavouriteListService.exception.FavouriteListAlreadyExists;
import com.niit.FavouriteListService.exception.MovieAlreadyExistsException;
import com.niit.FavouriteListService.repository.FavouriteRepository;
import com.niit.MovieService.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FavouriteServiceImpl implements FavouriteService{

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Override
    public Favourite createFavouriteAccount(String email) {
        Favourite favAccount = new Favourite(email,new ArrayList<>());
        return favouriteRepository.insert(favAccount);
    }

    @Override
    public Favourite getFavAccountByEmail(String email) {
        Favourite favAcc = favouriteRepository.findById(email).get() ;
//        System.out.println(favAcc);
        return favAcc;
    }

    @Override
    public Favourite addFavouriteList(String email, String favListName) throws FavouriteListAlreadyExists {
        Favourite favAccount = null;
        List<FavouriteList> favLists = null ;
        if(favouriteRepository.findById(email).isPresent()){
             favAccount = favouriteRepository.findById(email).get();
             favLists = favAccount.getFavouriteLists();

        }
        for(FavouriteList favlist : favLists){
            if(favlist.getFavListName().equalsIgnoreCase(favListName)){
                throw new FavouriteListAlreadyExists();
            }
        }
        favLists.add(new FavouriteList(favListName,new ArrayList<>()));
        favAccount.setFavouriteLists(favLists);
        System.out.println(favAccount);

        return favouriteRepository.save(favAccount);

    }

    @Override
    public Favourite addMovieToFavList(String email, String favListName, Movie movie) throws MovieAlreadyExistsException {
        Favourite favAccount = favouriteRepository.findById(email).get();
        List<FavouriteList> favLists = favAccount.getFavouriteLists();
        for(int i = 0 ; i < favLists.size() ; i++){
            if(favListName.equalsIgnoreCase(favLists.get(i).getFavListName())){
                FavouriteList favList = favLists.get(i);
                for(int j = 0 ; j < favList.getMovieList().size() ; j++){
                    if(favList.getMovieList().get(j).getId() == movie.getId()){
                        throw new MovieAlreadyExistsException();
                    }
                }
                favLists.get(i).getMovieList().add(movie);
            }
        }
        favAccount.setFavouriteLists(favLists);

        return favouriteRepository.save(favAccount);
    }

    @Override
    public FavouriteList getFavListByName(String email, String favListName) {
        Favourite favAccount = favouriteRepository.findById(email).get();
        FavouriteList favList = null ;
        for(int i = 0  ; i < favAccount.getFavouriteLists().size() ; i++){
            if(favAccount.getFavouriteLists().get(i).getFavListName().equalsIgnoreCase(favListName)){
                favList = favAccount.getFavouriteLists().get(i);
            }
        }
        return favList;
    }

    @Override
    public List<Favourite> getAllFavAcc() {
        return favouriteRepository.findAll();
    }

    @Override
    public Favourite deleteFavListByName(String email, String favListName) {
        Favourite favAcc = favouriteRepository.findById(email).get();
        List<FavouriteList> favouriteList = favAcc.getFavouriteLists();
        for(int i = 0 ; i < favouriteList.size() ; i ++){
            if(favListName.equalsIgnoreCase(favouriteList.get(i).getFavListName())){
                favouriteList.remove(i);
                break;
            }
        }
        favAcc.setFavouriteLists(favouriteList);
        return favouriteRepository.save(favAcc);
    }

    @Override
    public Favourite deleteFavAccBYEmail(String email) {
        favouriteRepository.deleteById(email);
        return favouriteRepository.findById(email).get();
    }

    @Override
    public boolean deleteAllFavAcc() {
        favouriteRepository.deleteAll();
        return true ;
    }

    @Override
    public Favourite deleteMovieFromFavList(String email,String favListName, int movieId) {
        Favourite favAccount = favouriteRepository.findById(email).get();
        List<FavouriteList> favLists = favAccount.getFavouriteLists();
        for(int i = 0 ; i < favLists.size() ; i++){
            if(favLists.get(i).getFavListName().equalsIgnoreCase(favListName)){
                FavouriteList favList = favLists.get(i);
                for (int j = 0 ; j < favList.getMovieList().size() ; j++ ){
//                    System.out.println(favList.getMovieList().size());
                    if(favList.getMovieList().get(j).getId() == movieId){
                        favList.getMovieList().remove(j);
                        favLists.set(i,favList);
                        System.out.println(favList);
                        break;
                    }
                }
                break;
            }
        }
        favAccount.setFavouriteLists(favLists);
        return favouriteRepository.save(favAccount);
    }
}
