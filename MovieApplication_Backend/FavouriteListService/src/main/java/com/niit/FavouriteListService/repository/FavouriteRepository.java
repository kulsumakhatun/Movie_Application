package com.niit.FavouriteListService.repository;

import com.niit.FavouriteListService.domain.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends MongoRepository<Favourite, String> {

}
