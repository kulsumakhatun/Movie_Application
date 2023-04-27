package com.niit.userService.UserService.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Favourite {
    @Id
    private String email;
    private List<FavouriteList> favouriteLists;

    public Favourite() {
    }

    public Favourite(String email, List<FavouriteList> favouriteLists) {
        this.email = email;
        this.favouriteLists = favouriteLists;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<FavouriteList> getFavouriteLists() {
        return favouriteLists;
    }

    public void setFavouriteLists(List<FavouriteList> favouriteLists) {
        this.favouriteLists = favouriteLists;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "email='" + email + '\'' +
                ", favouriteLists=" + favouriteLists +
                '}';
    }
}
