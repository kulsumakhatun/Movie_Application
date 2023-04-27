package com.niit.FavouriteListService.controller;

import com.niit.FavouriteListService.exception.FavouriteListAlreadyExists;
import com.niit.FavouriteListService.exception.MovieAlreadyExistsException;
import com.niit.FavouriteListService.services.FavouriteService;
import com.niit.MovieService.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favourite/")
public class FavouriteServiceController {

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping("favAcc/{email}")
    public ResponseEntity<?> createFavAccount(@PathVariable String email){

        return new ResponseEntity<>(favouriteService.createFavouriteAccount(email), HttpStatus.CREATED);
    }

    @PostMapping("favList/{email}")
    public ResponseEntity<?> createFavList(@PathVariable String email, @RequestBody String favListName) throws FavouriteListAlreadyExists {
        return new ResponseEntity<>(favouriteService.addFavouriteList(email,favListName),HttpStatus.CREATED);
    }

    @PostMapping("favList/addMovie/{email}/{name}")
    public ResponseEntity<?> addMovieToFavList(@PathVariable String email, @PathVariable String name, @RequestBody Movie movie){
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity(favouriteService.addMovieToFavList(email,name,movie),HttpStatus.CREATED);
        } catch (MovieAlreadyExistsException e) {
            responseEntity = new ResponseEntity(null,HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @GetMapping("getAccount/{email}")
    public ResponseEntity<?> getFavAccount(@PathVariable String email){
        return new ResponseEntity<>(favouriteService.getFavAccountByEmail(email),HttpStatus.OK);
    }

    @GetMapping("favList/get/{email}/{name}")
    public ResponseEntity<?> getFavAccByEmail(@PathVariable String email, @PathVariable String name){
        return new ResponseEntity<>(favouriteService.getFavListByName(email,name),HttpStatus.OK);
    }

    @GetMapping("allFavAcc")
    public ResponseEntity<?> getAllFavAcc(){
        return new ResponseEntity<>(favouriteService.getAllFavAcc(),HttpStatus.OK);
    }

    @DeleteMapping("favList/delete/{email}/{name}")
    public ResponseEntity<?> deleteFavListByName(@PathVariable String email, @PathVariable String name){
        return new ResponseEntity<>(favouriteService.deleteFavListByName(email,name),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("favLists/deleteAll")
    public ResponseEntity<?> deleteAllFavAcc(){
        return new ResponseEntity<>(favouriteService.deleteAllFavAcc(),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("favList/deleteMovie/{email}/{favListName}/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable String email,@PathVariable String favListName,  @PathVariable int id){
        return new ResponseEntity<>(favouriteService.deleteMovieFromFavList(email,favListName,id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("favList/deleteFavAcc/{email}")
    public ResponseEntity<?> deleteFavAcc(@PathVariable String email){
        return new ResponseEntity<>(favouriteService.deleteFavAccBYEmail(email),HttpStatus.ACCEPTED);
    }


}
